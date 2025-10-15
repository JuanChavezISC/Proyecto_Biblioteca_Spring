package com.biblioteca.mapper;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Libro;

@Component
public class LibroMapper {

	// Convertir de Entidad a DTO
	public LibroDto toDto(Libro libro) {
		
		if (libro == null) return null;
		
		
		LibroDto dto = new LibroDto();
		dto.setLibroId(libro.getLibroId());
		dto.setTitulo(libro.getTitulo());
		dto.setIsbn(libro.getIsbn());
		dto.setFechaPublicacion(libro.getFechaPublicacion());
		
		if (libro.getAutor() != null) {
			dto.setAutorId(libro.getAutor().getAutorId());
		}
		
		if (libro.getCategoria() != null) {
			dto.setCategoriaId(libro.getCategoria().getCategoriaId());
		}
		return dto;
	}
	
	// Convertir de DTO a Entidad
	
	public Libro toEntity(LibroDto dto) {
		if (dto == null) {
			return null;
		}
		
		Libro libro = new Libro();
		
		libro.setLibroId(dto.getLibroId());
		libro.setTitulo(dto.getTitulo());
		libro.setIsbn(dto.getIsbn());
		libro.setFechaPublicacion(dto.getFechaPublicacion());
		
		if (dto.getAutorId() != null) {
			Autor autor = new Autor();
			autor.setAutorId(dto.getAutorId());
			libro.setAutor(autor);
		}
		
		if (dto.getCategoriaId() != null) {
			Categoria categoria = new Categoria();
			categoria.setCategoriaId(dto.getCategoriaId());
			libro.setCategoria(categoria);
		}
		
		return libro;
	}
	
}
