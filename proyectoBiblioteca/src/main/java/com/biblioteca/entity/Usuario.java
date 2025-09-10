package com.biblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		name ="tbl_usuario",
		uniqueConstraints = @UniqueConstraint(
				name= "email_unique", // Indica el nombre de la restriccion
				columnNames = "email_address"
		)
)
public class Usuario {

	@Id
	@SequenceGenerator(
			name = "usuario_sequence",
			sequenceName = "usuario_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			generator = "usuario_sequence",
			strategy = GenerationType.SEQUENCE
	)
	private Long usuarioId;
	private String nombre;
	private String apellido;
	
	@Column(
			name = "email_address",
			nullable = false
	)
	private String email;
	
	
	// Constructores
	public Usuario() {
		super();
	}


	public Usuario(String nombre, String apellido, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}


	// Getters & Setters
	public Long getUsuarioId() {
		return usuarioId;
	}


	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ "]";
	}
	
	
	
}
