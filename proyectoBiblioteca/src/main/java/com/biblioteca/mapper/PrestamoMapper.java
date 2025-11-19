package com.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.biblioteca.dto.PrestamoDto;
import org.springframework.stereotype.Component;

import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.entity.Usuario;

@Component
public class PrestamoMapper {

	// Convertir de Entidad a DTO
	public PrestamoDto toDto(Prestamo prestamo) {
		if (prestamo == null) {
			return null;
		}

		return new PrestamoDto(
                prestamo.getPrestamoId(),
                prestamo.getLibro() != null ? prestamo.getLibro().getLibroId() : null,
                prestamo.getUsuario() != null ? prestamo.getUsuario().getUsuarioId() : null,
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                prestamo.isDevuelto()

        );
	}
	
	// Convertir de DTO a Entidad
	public Prestamo toEntity(PrestamoDto dto) {
		if (dto == null) {
			return null;
		}
		
		Prestamo prestamo = new Prestamo();
		prestamo.setPrestamoId(dto.prestamoId());
		prestamo.setFechaPrestamo(dto.fechaPrestamo());
		prestamo.setFechaDevolucion(dto.fechaDevolucion());
		prestamo.setDevuelto(dto.devuelto());
		
		// Relaciones: setear referencias con sus Id's (sin cargar tod o  el objeto)
		
		if (dto.libroId() != null) {
			Libro libro = new Libro();
			libro.setLibroId(dto.libroId());
			prestamo.setLibro(libro);
		}
		
		if (dto.usuarioId() != null) {
			Usuario usuario = new Usuario();
			usuario.setUsuarioId(dto.usuarioId());
			prestamo.setUsuario(usuario);
		}
		return prestamo;
	}
	
	// Metodos auxiliares de listas 
	
	public List<PrestamoDto> toDtoList(List<Prestamo> prestamos) {
		return prestamos.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	public List<Prestamo> toEntityList(List<PrestamoDto> dtos) {
		return dtos.stream()
				.map(this::toEntity)
				.collect(Collectors.toList());
	}
}
