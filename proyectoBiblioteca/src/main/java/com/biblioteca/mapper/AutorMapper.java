package com.biblioteca.mapper;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;

public class AutorMapper {

	public static AutorDto toDto(Autor autor) {
		if (autor == null) return null;
		
		
		AutorDto dto = new AutorDto();
		dto.setId(autor.getAutorId());
		dto.setNombre(autor.getNombre());
		dto.setApellido(autor.getApellido());
		dto.setNacionalidad(autor.getNacionalidad());
		dto.setFechaNacimiento(autor.getFechaNacimiento());
		
		return dto;
	}
	
	// Convertir de DTO a Entidad
	
	public static Autor toEntity(AutorDto dto) {
		
		if (dto == null) return null;
		
		Autor autor = new Autor();
		autor.setAutorId(dto.getId());
		autor.setNombre(dto.getNombre());
		autor.setApellido(dto.getApellido());
		autor.setNacionalidad(dto.getNacionalidad());
		autor.setFechaNacimiento(dto.getFechaNacimiento());

		return autor;
	}
}
