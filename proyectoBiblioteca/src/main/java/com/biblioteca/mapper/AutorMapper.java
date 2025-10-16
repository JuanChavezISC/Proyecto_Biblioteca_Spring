package com.biblioteca.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.biblioteca.dto.AutorDto;
import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Autor;

@Component
public class AutorMapper {

	// Convertir Autor a AutorDto
	public AutorDto toDto(Autor autor, boolean incluirLibros) {
		if (autor == null) return null;
		
		
		AutorDto dto = new AutorDto();
		dto.setAutorId(autor.getAutorId());
		dto.setNombre(autor.getNombre());
		dto.setApellido(autor.getApellido());
		dto.setNacionalidad(autor.getNacionalidad());
		dto.setFechaNacimiento(autor.getFechaNacimiento());
		
		if (incluirLibros && autor.getLibroList() != null && !autor.getLibroList().isEmpty()) {
			dto.setLibros(
					autor.getLibroList()
					.stream()
					.filter(Objects::nonNull)
					.map(libro -> {
						LibroDto libroDto = new LibroDto();
						libroDto.setLibroId(libro.getLibroId());
						libroDto.setTitulo(libro.getTitulo());
						libroDto.setIsbn(libro.getIsbn());
						libroDto.setFechaPublicacion(libro.getFechaPublicacion());
						
						libroDto.setAutorId(libro.getAutor() != null 
												? libro.getAutor().getAutorId() :null);
						libroDto.setCategoriaId(libro.getCategoria() != null
													? libro.getCategoria().getCategoriaId(): null);
						return libroDto;
					})
					.collect(Collectors.toList())
					);
		}
		
		return dto;
	}
	
	// Convertir de AutorDto a Entidad
	
	public  Autor toEntity(AutorDto dto) {
		
		if (dto == null) return null;
		
		Autor autor = new Autor();
		autor.setAutorId(dto.getAutorId());
		autor.setNombre(dto.getNombre());
		autor.setApellido(dto.getApellido());
		autor.setNacionalidad(dto.getNacionalidad());
		autor.setFechaNacimiento(dto.getFechaNacimiento());

		return autor;
	}
	
}
