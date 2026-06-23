package com.biblioteca.prestamos.dto;

public record UsuarioClientResponse(
        Long id,
        String nombre,
        String email,
        String telefono
) {}
