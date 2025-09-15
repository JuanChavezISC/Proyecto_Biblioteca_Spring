package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class LibroDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String isbn;
	private LocalDate fechaPublicacion;
	private Long autorId;
	private Long categoriaId;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Long getAutorId() {
		return autorId;
	}
	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	
	
	
}
