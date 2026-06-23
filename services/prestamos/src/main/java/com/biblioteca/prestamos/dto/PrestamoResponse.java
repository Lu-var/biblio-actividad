package com.biblioteca.prestamos.dto;

import java.time.LocalDate;

public record PrestamoResponse(
        Long id,
        Long libroId,
        Long usuarioId,
        LocalDate fechaPrestamo,
        LocalDate fechaDevolucion,
        String libroTitulo,
        String usuarioNombre
) {}
