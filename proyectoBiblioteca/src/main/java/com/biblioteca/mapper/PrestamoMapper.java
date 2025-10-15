package com.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.PrestamoDto;
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
		
		PrestamoDto dto = new PrestamoDto();
		dto.setPrestamoId(prestamo.getPrestamoId());
		dto.setFechaPrestamo(prestamo.getFechaPrestamo());
		dto.setFechaDevolucion(prestamo.getFechaDevolucion());
		dto.setDevuelto(prestamo.isDevuelto());
		
		//Relacion con Libro y Usuarios (solo Id's)
		if (prestamo.getLibro() != null) {
			dto.setLibroId(prestamo.getLibro().getLibroId());
		}
		
		if (prestamo.getUsuario() != null) {
			dto.setUsuarioId(prestamo.getUsuario().getUsuarioId());
		}
		return dto;
	}
	
	// Convertir de DTO a Entidad
	public Prestamo toEntity(PrestamoDto dto) {
		if (dto == null) {
			return null;
		}
		
		Prestamo prestamo = new Prestamo();
		prestamo.setPrestamoId(dto.getPrestamoId());
		prestamo.setFechaPrestamo(dto.getFechaPrestamo());
		prestamo.setFechaDevolucion(dto.getFechaDevolucion());
		prestamo.setDevuelto(dto.getDevuelto());
		
		// Relaciones: setear referencias con sus Id's (sin cargar todo el objeto)
		
		if (dto.getLibroId() != null) {
			Libro libro = new Libro();
			libro.setLibroId(dto.getLibroId());
			prestamo.setLibro(libro);
		}
		
		if (dto.getUsuarioId() != null) {
			Usuario usuario = new Usuario();
			usuario.setUsuarioId(dto.getUsuarioId());
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
