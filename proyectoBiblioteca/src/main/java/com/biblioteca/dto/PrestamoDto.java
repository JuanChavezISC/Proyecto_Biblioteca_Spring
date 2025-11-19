package com.biblioteca.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PrestamoDto(
        Long prestamoId,

        @NotNull(message = "El libro es obligatorio")
        @Positive(message = "El Id del libro debe ser positivo")
        Long libroId,

        @NotNull(message = "El usuario es obligatorio")
        @Positive(message = "El Id del usuario es obligatorio")
        Long usuarioId,

        @PastOrPresent(message = "La fecha de prestamo no puede ser futura")
        LocalDate fechaPrestamo,

        @FutureOrPresent(message = "La fecha de devolucion no puede ser en el pasado")
        LocalDate fechaDevolucion,

        @NotNull(message = "Debe indicar si el libro fue devuelto o no")
        Boolean devuelto
) { }
