package com.biblioteca.entity;

import java.time.LocalDate;

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
		name = "tbl_prestamo"
)
public class Prestamo {

	@Id
	@SequenceGenerator(
			name = "prestamo_sequence",
			sequenceName = "prestamo_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			generator = "prestamo_sequence",
			strategy = GenerationType.SEQUENCE
			)
	private Long prestamoId;
	@ManyToOne
	@JoinColumn(
			name = "libro_id",
			referencedColumnName = "libroId"
			)
	private Libro libro;
	@ManyToOne
	@JoinColumn(
			name = "usuario_id",
			referencedColumnName = "usuarioId"
			)
	private Usuario usuario;
	
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private boolean devuelto;
	
	// Constructores
	public Prestamo() {
		super();
	}

	public Prestamo(Libro libro, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion,
			boolean devuelto) {
		super();
		this.libro = libro;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.devuelto = devuelto;
	}
	
	// Getters & Setters

	public Long getPrestamoId() {
		return prestamoId;
	}

	public void setPrestamoId(Long prestamoId) {
		this.prestamoId = prestamoId;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public boolean isDevuelto() {
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}

	@Override
	public String toString() {
		return "Prestamo [prestamoId=" + prestamoId + ", libro=" + libro + ", usuario=" + usuario + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", devuelto=" + devuelto + "]";
	}
	
	
	
	
}
