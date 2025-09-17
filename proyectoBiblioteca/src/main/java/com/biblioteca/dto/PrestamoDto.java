package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;



public class PrestamoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long libroId;
	private Long usuarioId;
	
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
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
	
}
