package com.biblioteca.service.categoria;

import java.util.List;

import com.biblioteca.dto.CategoriaDto;
import com.biblioteca.entity.Categoria;

public interface ICategoriaService {

	List<Categoria> findAllCategories();
	
	Categoria saveCategory(CategoriaDto categoria);
	
	Categoria updateCategory(Long id, CategoriaDto categoria);
	
	void deleteCategory(Long id);
}
