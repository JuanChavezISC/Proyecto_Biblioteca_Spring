package com.biblioteca.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(
		name = "tbl_autor",
        uniqueConstraints = @UniqueConstraint(
                name = "autor_unique",
                columnNames = {"nombre", "apellido", "nacionalidad", "fecha_nacimiento"}
        )
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
    @Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@OneToMany(mappedBy = "autor")
	private List<Libro> libroList;
	

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

	public List<Libro> getLibroList() {
		return libroList;
	}
	
	public void setLibroList(List<Libro> libroList) {
		this.libroList = libroList;
	}
	// toString
	@Override
	public String toString() {
		return "Autor [autorId=" + autorId + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad="
				+ nacionalidad + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
	
	
}
