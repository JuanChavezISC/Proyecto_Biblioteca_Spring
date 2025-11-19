package com.biblioteca.service.categoria;

import com.biblioteca.dto.CategoriaDto;

import java.util.List;

public interface ICategoriaService {
	
	List<CategoriaDto> findAllCategories();
	CategoriaDto findCategoriaById(Long id);	
	CategoriaDto saveCategory(CategoriaDto categoria);
	CategoriaDto updateCategory(Long id, CategoriaDto categoria);
	void deleteCategory(Long id);
}
