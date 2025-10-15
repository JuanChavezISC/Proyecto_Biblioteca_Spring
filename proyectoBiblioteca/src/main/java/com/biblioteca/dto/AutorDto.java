package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class AutorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long autorId;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 50)
	private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(max = 80)
	private String apellido;
	
	@NotBlank(message = "La nacionalidad es obligatoria")
	@Size(max = 80)
	private String nacionalidad;
	
	@PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
	private LocalDate fechaNacimiento;
	
	@Valid
	private List<LibroDto> libros;
	
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
	
	public Long getAutorId() {
		return autorId;
	}
	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	public List<LibroDto> getLibros() {
		return libros;
	}
	public void setLibros(List<LibroDto> libros) {
		this.libros = libros;
	}


	
	
}
