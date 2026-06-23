package com.biblioteca.prestamos.dto;

public record LibroClientResponse(
        Long id,
        String titulo,
        String autor,
        String isbn,
        boolean disponible
) {}
