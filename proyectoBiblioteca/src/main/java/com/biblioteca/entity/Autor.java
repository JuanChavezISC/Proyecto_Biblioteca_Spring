package com.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(
		name = "tbl_autor"
)
public class Autor {

	@Id
	@SequenceGenerator(
			name = "autor_sequence",
			sequenceName = "autor_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			generator = "autor_sequence",
			strategy = GenerationType.SEQUENCE
	)
	private Long autorId;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	
	// Constructores 
	public Autor() {
		super();
	}

	public Autor(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	// Getters & Setters
	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	// toString
	@Override
	public String toString() {
		return "Autor [autorId=" + autorId + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad="
				+ nacionalidad + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
	
	
}
