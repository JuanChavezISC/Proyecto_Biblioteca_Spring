package com.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(
		name = "tbl_libro"
)
public class Libro {

	@Id
	@SequenceGenerator(
			name = "libro_sequence",
			sequenceName = "libro_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			generator = "libro_sequence",
			strategy = GenerationType.SEQUENCE
			)
	private Long libroId;
	private String titulo;
	@Column(unique = true)
	private String isbn;
	private LocalDate fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(
			name = "autor_id",
			referencedColumnName = "autorId"
	)
	private Autor autor;
	@ManyToOne
	@JoinColumn(
			name = "categoria_id",
			referencedColumnName = "categoriaId"
	)
	private Categoria categoria;
	
	// Constructores
	public Libro() {
		super();
	}


	public Libro(String titulo, String isbn, LocalDate fechaPublicacion, Autor autor, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
		this.categoria = categoria;
	}


	public Long getLibroId() {
		return libroId;
	}


	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}


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


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public String toString() {
		return "Libro [libroId=" + libroId + ", titulo=" + titulo + ", isbn=" + isbn + ", fechaPublicacion="
				+ fechaPublicacion + ", autor=" + autor + ", categoria=" + categoria + "]";
	}
	
	
}
