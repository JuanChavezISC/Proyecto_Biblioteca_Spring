package com.biblioteca.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.dto.LibroDto;
import org.springframework.stereotype.Component;
import com.biblioteca.entity.Autor;

@Component
public class AutorMapper {

	// Convertir Autor a AutorDto
	public AutorDto toDto(Autor autor, boolean incluirLibros) {
		if (autor == null) return null;

        // Si no se incluyen libros, devolver sin lista
        if (!incluirLibros){
            return new AutorDto(
                    autor.getAutorId(),
                    autor.getNombre(),
                    autor.getApellido(),
                    autor.getNacionalidad(),
                    autor.getFechaNacimiento(),
                    null
            );
        }

        // Si incluyen libros
        var librosDto = autor.getLibroList() == null ? null :
                autor.getLibroList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(libro -> new LibroDto(
                                libro.getLibroId(),
                                libro.getTitulo(),
                                libro.getIsbn(),
                                libro.getFechaPublicacion(),
                                libro.getAutor() != null ? libro.getAutor().getAutorId() :null,
                                libro.getCategoria() != null ? libro.getCategoria().getCategoriaId() : null
                        ))
                        .collect(Collectors.toList());

		return new AutorDto(
                autor.getAutorId(),
                autor.getNombre(),
                autor.getApellido(),
                autor.getNacionalidad(),
                autor.getFechaNacimiento(),
                librosDto
        );
	}

	// Convertir de AutorDto a Entidad
	public  Autor toEntity(AutorDto dto) {
		if (dto == null) return null;
		
		Autor autor = new Autor();
		autor.setAutorId(dto.autorId());
		autor.setNombre(dto.nombre());
		autor.setApellido(dto.apellido());
		autor.setNacionalidad(dto.nacionalidad());
		autor.setFechaNacimiento(dto.fechaNacimiento());

		return autor;
	}
	
}
