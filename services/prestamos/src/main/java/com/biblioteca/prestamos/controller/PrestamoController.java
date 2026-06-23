package com.biblioteca.prestamos.controller;

import com.biblioteca.prestamos.dto.PrestamoRequest;
import com.biblioteca.prestamos.dto.PrestamoResponse;
import com.biblioteca.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> create(@Valid @RequestBody PrestamoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoResponse> registrarDevolucion(@PathVariable Long id) {
        return ResponseEntity.ok(service.registrarDevolucion(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PrestamoResponse>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getByUsuario(usuarioId));
    }
}
