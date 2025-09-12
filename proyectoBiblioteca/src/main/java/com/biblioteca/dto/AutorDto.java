package com.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.biblioteca.entity.Libro;

public class AutorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private LocalDate fechaNacimiento;
	private List<Libro> libroList;
	
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
	public List<Libro> getLibroList() {
		return libroList;
	}
	public void setLibroList(List<Libro> libroList) {
		this.libroList = libroList;
	}
	
	
}
