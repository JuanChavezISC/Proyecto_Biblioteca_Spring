package com.biblioteca.service.categoria;

import java.util.List;

import com.biblioteca.dto.CategoriaDto;

public interface ICategoriaService {
	
	List<CategoriaDto> findAllCategories();
	CategoriaDto findCategoriaById(Long id);	
	CategoriaDto saveCategory(CategoriaDto categoria);
	CategoriaDto updateCategory(Long id, CategoriaDto categoria);
	void deleteCategory(Long id);
}
