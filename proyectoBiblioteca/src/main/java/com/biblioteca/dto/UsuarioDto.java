package com.biblioteca.dto;

import java.io.Serializable;
import java.util.List;

import com.biblioteca.entity.Prestamo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long usuarioId;

	@NotBlank(message = "El nombre debe ser obligatorio")
	@Size(max = 120)
	private String nombre;
	
	@NotBlank(message = "El apellido debe ser obligatorio")
	@Size(max = 120)
	private String apellido;
	
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe tener un formato valido. Ejemplo(usuario@example.com")
	private String email;
	
	@Valid
	private List<Prestamo> prestamo;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Prestamo> getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(List<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
