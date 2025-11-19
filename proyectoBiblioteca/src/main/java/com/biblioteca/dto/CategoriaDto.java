package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaDto(
        Long categoriaId,

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(max = 120)
        String descripcion
) {
}
