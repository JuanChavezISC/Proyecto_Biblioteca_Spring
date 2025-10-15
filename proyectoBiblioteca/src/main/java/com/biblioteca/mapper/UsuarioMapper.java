package com.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.entity.Usuario;

@Component
public class UsuarioMapper {

	
	private final PrestamoMapper prestamoMapper;
	
	
	public UsuarioMapper(PrestamoMapper prestamoMapper) {
		super();
		this.prestamoMapper = prestamoMapper;
	}

	// Convertir de entidad a DTO
	public UsuarioDto toDto(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		
		UsuarioDto dto = new UsuarioDto();
		dto.setUsuarioId(usuario.getUsuarioId());
		dto.setNombre(usuario.getNombre());
		dto.setApellido(usuario.getApellido());
		dto.setEmail(usuario.getEmail());
		
		if (usuario.getPrestamo() != null) {
			dto.setPrestamo(prestamoMapper.toDtoList(usuario.getPrestamo()));
		}
		
		return dto;
	}
	
	// Convertir de DTO a Entidad
	public Usuario toEntity(UsuarioDto dto) {
		if (dto == null) {
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(dto.getUsuarioId());
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		usuario.setEmail(dto.getEmail());
		
		if (dto.getPrestamo() != null) {
			usuario.setPrestamo(prestamoMapper.toEntityList(dto.getPrestamo()));
		}
		return usuario;
	}
	
	// Metodos auxiliares para listas
	
	public List<UsuarioDto> toDTOList(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	
	public List<Usuario> toEntityList(List<UsuarioDto> dtos) {
		return dtos.stream()
				.map(this::toEntity)
				.collect(Collectors.toList());
	}
}
