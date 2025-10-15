package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;



public class PrestamoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long prestamoId; 
	
	@NotNull(message = "El libro es obligatorio")
	@Positive(message = "El Id del libro debe ser positivo")
	private Long libroId;
	
	@NotNull(message = "El usuario es obligatorio")
	@Positive(message = "El Id del usuario es obligatorio")
	private Long usuarioId;
	
	@PastOrPresent(message = "La fecha de prestamo no puede ser futura")
	private LocalDate fechaPrestamo;
	
	@FutureOrPresent(message = "La fecha de devolucion no puede ser en el pasado")
	private LocalDate fechaDevolucion;
	
	@NotNull(message = "Debe indicar si el libro fue devuelto o no")
	private Boolean devuelto;
	
	public Long getLibroId() {
		return libroId;
	}
	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public Boolean getDevuelto() {
		return devuelto;
	}
	public void setDevuelto(Boolean devuelto) {
		this.devuelto = devuelto;
	}
	public Long getPrestamoId() {
		return prestamoId;
	}
	public void setPrestamoId(Long prestamoId) {
		this.prestamoId = prestamoId;
	}
	
}
