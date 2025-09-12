package com.biblioteca.dto;

import java.io.Serializable;

public class CategoriaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
