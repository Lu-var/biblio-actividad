package com.biblioteca.libros.service;

import com.biblioteca.libros.dto.LibroRequest;
import com.biblioteca.libros.dto.LibroResponse;
import com.biblioteca.libros.model.Libro;
import com.biblioteca.libros.repository.LibroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public List<LibroResponse> getAll() {
        log.info("Getting all libros");
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public LibroResponse getById(Long id) {
        log.info("Getting libro by id: {}", id);
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Libro with ID " + id + " not found"));
    }

    public LibroResponse create(LibroRequest request) {
        log.info("Creating libro: {}", request.titulo());
        if (repository.findByIsbn(request.isbn()).isPresent()) {
            throw new jakarta.persistence.EntityExistsException("ISBN " + request.isbn() + " already exists");
        }
        Libro libro = new Libro();
        libro.setTitulo(request.titulo());
        libro.setAutor(request.autor());
        libro.setIsbn(request.isbn());
        libro.setDisponible(true);
        Libro saved = repository.save(libro);
        return toResponse(saved);
    }

    public LibroResponse update(Long id, LibroRequest request) {
        log.info("Updating libro id: {}", id);
        return repository.findById(id)
                .map(libro -> {
                    libro.setTitulo(request.titulo());
                    libro.setAutor(request.autor());
                    libro.setIsbn(request.isbn());
                    Libro saved = repository.save(libro);
                    return toResponse(saved);
                })
                .orElseThrow(() -> new EntityNotFoundException("Libro with ID " + id + " not found"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Libro with ID " + id + " not found");
        }
        repository.deleteById(id);
        log.info("Libro id: {} deleted", id);
    }

    public LibroResponse toggleDisponibilidad(Long id) {
        log.info("Toggling disponibilidad for libro id: {}", id);
        return repository.findById(id)
                .map(libro -> {
                    libro.setDisponible(!libro.isDisponible());
                    Libro saved = repository.save(libro);
                    return toResponse(saved);
                })
                .orElseThrow(() -> new EntityNotFoundException("Libro with ID " + id + " not found"));
    }

    private LibroResponse toResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.isDisponible()
        );
    }
}
