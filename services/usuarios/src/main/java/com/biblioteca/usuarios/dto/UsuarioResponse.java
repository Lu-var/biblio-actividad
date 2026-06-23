package com.biblioteca.usuarios.dto;

public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        String telefono
) {}
