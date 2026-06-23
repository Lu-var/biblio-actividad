package com.biblioteca.libros.dto;

import jakarta.validation.constraints.NotBlank;

public record LibroRequest(
        @NotBlank String titulo,
        @NotBlank String autor,
        @NotBlank String isbn
) {}
