package com.biblioteca.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroUsuarioDto(
        @NotBlank
                (message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email no válido")
        String email,

        @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
        String telefono,

        @NotBlank(message = "La dirección es obligatoria")
        String direccion,

        @NotBlank(message = "la ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "El username es obligatorio")
        @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
        String username,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {
}
