package com.biblioteca.mapper;

import com.biblioteca.dto.LibroDto;
import org.springframework.stereotype.Component;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Libro;

@Component
public class LibroMapper {

	// Convertir de Entidad a DTO
	public LibroDto toDto(Libro libro) {
		if (libro == null) return null;

		return new LibroDto(
                libro.getLibroId(),
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getFechaPublicacion(),
                libro.getAutor() != null ? libro.getAutor().getAutorId() : null,
                libro.getCategoria() != null ? libro.getCategoria().getCategoriaId() : null
        );
	}
	
	// Convertir de DTO a Entidad
	public Libro toEntity(LibroDto dto) {
		if (dto == null) {
			return null;
		}
		
		Libro libro = new Libro();
		
		libro.setLibroId(dto.libroId());
		libro.setTitulo(dto.titulo());
		libro.setIsbn(dto.isbn());
		libro.setFechaPublicacion(dto.fechaPublicacion());
		
		if (dto.autorId() != null) {
			Autor autor = new Autor();
			autor.setAutorId(dto.autorId());
			libro.setAutor(autor);
		}
		
		if (dto.categoriaId() != null) {
			Categoria categoria = new Categoria();
			categoria.setCategoriaId(dto.categoriaId());
			libro.setCategoria(categoria);
		}
		
		return libro;
	}
	
}
