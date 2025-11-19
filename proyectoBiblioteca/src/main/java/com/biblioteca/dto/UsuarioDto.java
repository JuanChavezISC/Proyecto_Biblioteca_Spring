package com.biblioteca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDto(
        Long usuarioId,

        @NotBlank(message = "El nombre debe ser obligatorio")
        @Size(max = 120)
        String nombre,

        @NotBlank(message = "El apellido debe ser obligatorio")
        @Size(max = 120)
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe tener un formato valido. Ejemplo(usuario@example.com")
        String email,

        @Valid
        List<PrestamoDto> prestamos
) { }
