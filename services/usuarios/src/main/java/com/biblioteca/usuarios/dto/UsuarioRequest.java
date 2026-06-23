package com.biblioteca.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        String telefono
) {}
