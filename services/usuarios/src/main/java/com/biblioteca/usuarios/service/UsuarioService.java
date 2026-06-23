package com.biblioteca.usuarios.service;

import com.biblioteca.usuarios.dto.UsuarioRequest;
import com.biblioteca.usuarios.dto.UsuarioResponse;
import com.biblioteca.usuarios.model.Usuario;
import com.biblioteca.usuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioResponse> getAll() {
        log.info("Getting all usuarios");
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UsuarioResponse getById(Long id) {
        log.info("Getting usuario by id: {}", id);
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Usuario with ID " + id + " not found"));
    }

    public UsuarioResponse create(UsuarioRequest request) {
        log.info("Creating usuario: {}", request.email());
        if (repository.findByEmail(request.email()).isPresent()) {
            throw new EntityExistsException("Email " + request.email() + " already exists");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setTelefono(request.telefono());
        Usuario saved = repository.save(usuario);
        return toResponse(saved);
    }

    public UsuarioResponse update(Long id, UsuarioRequest request) {
        log.info("Updating usuario id: {}", id);
        return repository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(request.nombre());
                    usuario.setEmail(request.email());
                    usuario.setTelefono(request.telefono());
                    Usuario saved = repository.save(usuario);
                    return toResponse(saved);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuario with ID " + id + " not found"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario with ID " + id + " not found");
        }
        repository.deleteById(id);
        log.info("Usuario id: {} deleted", id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTelefono()
        );
    }
}
