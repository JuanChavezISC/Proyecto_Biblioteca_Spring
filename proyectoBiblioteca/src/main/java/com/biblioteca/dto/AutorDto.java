package com.biblioteca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record AutorDto(
         Long autorId,

         @NotBlank(message = "El nombre es obligatorio")
         @Size(max = 50)
         String nombre,

         @NotBlank(message = "El apellido es obligatorio")
         @Size(max = 80)
         String apellido,

         @NotBlank(message = "La nacionalidad es obligatoria")
         @Size(max = 80)
         String nacionalidad,

         @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
         LocalDate fechaNacimiento,

         @Valid
         List<LibroDto> libros
) { }
