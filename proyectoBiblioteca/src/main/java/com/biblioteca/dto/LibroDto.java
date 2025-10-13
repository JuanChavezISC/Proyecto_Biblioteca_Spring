package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class LibroDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "El titulo debe ser obligatorio")
	@Size(max = 120)
	private String titulo;
	
	@NotBlank(message = "El isbn es obligatorio")
	@Size(min = 13, max = 14, message = "El isbn debe tener 13 digitos")
	private String isbn;
	
	@PastOrPresent(message = "La fecha de publicacion no puede ser futura")
	private LocalDate fechaPublicacion;
	
	@NotNull(message = "El autor es obligatorio")
	@Positive(message = "El Id del autor debe ser positivo")
	private Long autorId;
	
	@NotNull(message = "La categoria es obligatoria")
	@Positive(message = "El Id de la categoria es obligatoria")
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
