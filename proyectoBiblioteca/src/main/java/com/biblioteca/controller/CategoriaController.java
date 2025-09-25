package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.CategoriaDto;
import com.biblioteca.entity.Categoria;
import com.biblioteca.service.categoria.ICategoriaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class CategoriaController {

	@Autowired
	ICategoriaService categoriaService;
	
	@GetMapping("/categorias")
	@ResponseStatus (HttpStatus.OK)
	public List<Categoria> findAllCategories() {
		return categoriaService.findAllCategories();
	}
	
	@GetMapping("/categorias/{id}")
	public Categoria findCategoriaById(@PathVariable  Long id) {
		return categoriaService.findCategoriaById(id);
	}
	
	
	@PostMapping("/categorias")
	public Categoria saveCategory(@RequestBody CategoriaDto categoria) {
		return categoriaService.saveCategory(categoria);
	}
	
	@PutMapping("/categorias/{id}")
	public Categoria updateCategory(@PathVariable Long id, @RequestBody CategoriaDto categoria) {
		return categoriaService.updateCategory(id, categoria);
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoriaService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}
