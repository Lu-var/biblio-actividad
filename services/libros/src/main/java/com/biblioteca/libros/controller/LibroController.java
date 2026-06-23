package com.biblioteca.libros.controller;

import com.biblioteca.libros.dto.LibroRequest;
import com.biblioteca.libros.dto.LibroResponse;
import com.biblioteca.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LibroResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<LibroResponse> create(@Valid @RequestBody LibroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> update(@PathVariable Long id, @Valid @RequestBody LibroRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<LibroResponse> toggleDisponibilidad(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleDisponibilidad(id));
    }
}
