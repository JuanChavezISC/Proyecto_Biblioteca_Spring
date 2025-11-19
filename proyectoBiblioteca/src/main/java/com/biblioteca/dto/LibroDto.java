package com.biblioteca.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record LibroDto(
        Long libroId,

        @NotBlank(message = "El titulo debe ser obligatorio")
        @Size(max = 120)
        String titulo,

        @NotBlank(message = "El isbn es obligatorio")
        @Size(min = 13, max = 14, message = "El isbn debe tener 13 digitos")
        String isbn,

        @PastOrPresent(message = "La fecha de publicacion no puede ser futura")
        LocalDate fechaPublicacion,

        @NotNull(message = "El autor es obligatorio")
        @Positive(message = "El Id del autor debe ser positivo")
        Long autorId,

        @NotNull(message = "La categoria es obligatoria")
        @Positive(message = "El Id de la categoria es obligatoria")
        Long categoriaId
) {
}
