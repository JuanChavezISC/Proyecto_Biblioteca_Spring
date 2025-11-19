package com.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.dto.UsuarioDto;
import org.springframework.stereotype.Component;

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

        List<PrestamoDto> prestamosDto = null;

		if (usuario.getPrestamo() != null) {
			prestamosDto= prestamoMapper.toDtoList(usuario.getPrestamo());
		}
		
		return new UsuarioDto(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                prestamosDto);
	}
	
	// Convertir de DTO a Entidad
	public Usuario toEntity(UsuarioDto dto) {
		if (dto == null) {
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(dto.usuarioId());
		usuario.setNombre(dto.nombre());
		usuario.setApellido(dto.apellido());
		usuario.setEmail(dto.email());
		
		if (dto.prestamos() != null) {
			usuario.setPrestamo(prestamoMapper.toEntityList(dto.prestamos()));
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
