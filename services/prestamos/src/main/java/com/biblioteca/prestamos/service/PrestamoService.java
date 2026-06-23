package com.biblioteca.prestamos.service;

import com.biblioteca.prestamos.client.LibroRestClient;
import com.biblioteca.prestamos.client.UsuarioRestClient;
import com.biblioteca.prestamos.dto.LibroClientResponse;
import com.biblioteca.prestamos.dto.PrestamoRequest;
import com.biblioteca.prestamos.dto.PrestamoResponse;
import com.biblioteca.prestamos.dto.UsuarioClientResponse;
import com.biblioteca.prestamos.model.Prestamo;
import com.biblioteca.prestamos.repository.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PrestamoService {

    private final PrestamoRepository repository;
    private final LibroRestClient libroClient;
    private final UsuarioRestClient usuarioClient;

    public PrestamoService(PrestamoRepository repository,
                           LibroRestClient libroClient,
                           UsuarioRestClient usuarioClient) {
        this.repository = repository;
        this.libroClient = libroClient;
        this.usuarioClient = usuarioClient;
    }

    public List<PrestamoResponse> getAll() {
        log.info("Getting all prestamos");
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public PrestamoResponse getById(Long id) {
        log.info("Getting prestamo by id: {}", id);
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Prestamo with ID " + id + " not found"));
    }

    public PrestamoResponse create(PrestamoRequest request) {
        log.info("Creating prestamo: libroId={}, usuarioId={}", request.libroId(), request.usuarioId());

        LibroClientResponse libro = validateLibro(request.libroId());
        if (!libro.disponible()) {
            throw new IllegalArgumentException("Libro with ID " + request.libroId() + " is not available");
        }
        validateUsuario(request.usuarioId());

        libroClient.toggleDisponibilidad(request.libroId());

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(request.libroId());
        prestamo.setUsuarioId(request.usuarioId());
        prestamo.setFechaPrestamo(LocalDate.now());

        Prestamo saved = repository.save(prestamo);
        return toResponse(saved);
    }

    public PrestamoResponse registrarDevolucion(Long id) {
        log.info("Registering return for prestamo id: {}", id);
        return repository.findById(id)
                .map(prestamo -> {
                    if (prestamo.getFechaDevolucion() != null) {
                        throw new IllegalArgumentException("Prestamo with ID " + id + " already returned");
                    }
                    prestamo.setFechaDevolucion(LocalDate.now());
                    Prestamo saved = repository.save(prestamo);

                    try {
                        libroClient.toggleDisponibilidad(prestamo.getLibroId());
                    } catch (RestClientException e) {
                        log.warn("Could not update libro disponibilidad: {}", e.getMessage());
                    }

                    return toResponse(saved);
                })
                .orElseThrow(() -> new EntityNotFoundException("Prestamo with ID " + id + " not found"));
    }

    public List<PrestamoResponse> getByUsuario(Long usuarioId) {
        log.info("Getting prestamos for usuario id: {}", usuarioId);
        return repository.findByUsuarioId(usuarioId).stream()
                .map(this::toResponse)
                .toList();
    }

    private LibroClientResponse validateLibro(Long libroId) {
        try {
            return libroClient.getById(libroId);
        } catch (RestClientException e) {
            log.warn("Libro with id {} not found: {}", libroId, e.getMessage());
            throw new EntityNotFoundException("Libro with ID " + libroId + " not found");
        }
    }

    private void validateUsuario(Long usuarioId) {
        try {
            usuarioClient.getById(usuarioId);
        } catch (RestClientException e) {
            log.warn("Usuario with id {} not found: {}", usuarioId, e.getMessage());
            throw new EntityNotFoundException("Usuario with ID " + usuarioId + " not found");
        }
    }

    private PrestamoResponse toResponse(Prestamo prestamo) {
        String libroTitulo = null;
        String usuarioNombre = null;

        try {
            LibroClientResponse libro = libroClient.getById(prestamo.getLibroId());
            libroTitulo = libro.titulo();
        } catch (RestClientException e) {
            log.warn("Could not fetch libro title: {}", e.getMessage());
        }

        try {
            UsuarioClientResponse usuario = usuarioClient.getById(prestamo.getUsuarioId());
            usuarioNombre = usuario.nombre();
        } catch (RestClientException e) {
            log.warn("Could not fetch usuario name: {}", e.getMessage());
        }

        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getLibroId(),
                prestamo.getUsuarioId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                libroTitulo,
                usuarioNombre
        );
    }
}
