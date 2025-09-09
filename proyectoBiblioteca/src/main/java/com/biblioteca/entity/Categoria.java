package com.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		name = "tbl_categoria",
		uniqueConstraints = @UniqueConstraint(
				name = "categoria_unique",
				columnNames = "descripcion"
		)
)
public class Categoria {

	@Id
	@SequenceGenerator(
			name = "categoria_sequence",
			sequenceName = "categoria_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			generator = "categoria_sequence",
			strategy = GenerationType.SEQUENCE
	)
	private Long categoriaId;
	private String descripcion;
	
	
	public Categoria() {
		super();
	}


	public Categoria(Long categoriaId, String descripcion) {
		super();
		this.categoriaId = categoriaId;
		this.descripcion = descripcion;
	}


	public Long getCategoriaId() {
		return categoriaId;
	}


	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
