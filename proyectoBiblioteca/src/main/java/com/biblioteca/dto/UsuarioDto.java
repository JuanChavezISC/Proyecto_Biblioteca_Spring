package com.biblioteca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
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

        @NotBlank(message = "El telefono es obligatorio")
        @Size(min = 10, max = 10, message = "El telefono debe ser de 10 digitos")
        String telefono,

        @NotBlank(message = "La direccion es obligatoria")
        @Size(max = 120)
        String direccion,
        @NotBlank(message = "La ciudad es obligatoria")
        @Size(max = 60)
        String ciudad,

        LocalDateTime fechaRegistro, // Se llena automaticamente
        Boolean activo,

        @Valid
        List<PrestamoDto> prestamos
) { }
