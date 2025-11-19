package com.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.biblioteca.dto.CategoriaDto;
import org.springframework.stereotype.Component;

import com.biblioteca.entity.Categoria;

@Component
public class CategoriaMapper {

	// Convertir de Entidad a DTO
	public CategoriaDto toDto(Categoria categoria) {
		
		if (categoria == null) {
			return null;
		}

		return new CategoriaDto(
                categoria.getCategoriaId(),
                categoria.getDescripcion()
        );
	}

	// Convertir de DTO a Entidad
	public Categoria toEntity(CategoriaDto dto) {
		if (dto == null) {
			return null;
		}
		
		Categoria categoria = new Categoria();
		categoria.setCategoriaId(dto.categoriaId());
		categoria.setDescripcion(dto.descripcion());
		
		return categoria;
	}
	
	// --- Metodos Auxiliares ---
	
	public List<CategoriaDto> toDtoList(List<Categoria> categorias) {
		return categorias.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	
	public List<Categoria> toEntityList(List<CategoriaDto> dtos) {
		return dtos.stream()
				.map(this::toEntity)
				.collect(Collectors.toList());
	}
}
