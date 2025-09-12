package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.CategoriaDto;
import com.biblioteca.entity.Categoria;
import com.biblioteca.service.categoria.ICategoriaService;

@RestController
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	ICategoriaService categoriaService;
	
	@GetMapping("/findAllCategories")
	public List<Categoria> findAllCategories() {
		return categoriaService.findAllCategories();
	}
	
	@PostMapping("/saveCategory")
	public Categoria saveCategory(@RequestBody CategoriaDto categoria) {
		return categoriaService.saveCategory(categoria);
	}
	
	@PutMapping("/updateCategory/{id}")
	public Categoria updateCategory(@PathVariable Long id, @RequestBody CategoriaDto categoria) {
		return categoriaService.updateCategory(id, categoria);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoriaService.deleteCategory(id);
		return "Categoria deleted successfully";
	}
}
