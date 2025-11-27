package com.biblioteca.controller;

import java.net.URI;
import java.util.List;


import com.biblioteca.dto.CategoriaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.service.categoria.ICategoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
@Validated
public class CategoriaController {

	private final ICategoriaService categoriaService;

	public CategoriaController(ICategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/categorias")
	public ResponseEntity<List<CategoriaDto>> categories() {
		return ResponseEntity.ok(categoriaService.findAllCategories());
	}

    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(categoriaService.findCategoriaById(id));
	}

    // ADMIN y LIBRARIAN puede crear categorias
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaDto> saveCategory(@Valid @RequestBody CategoriaDto categoria, 
																UriComponentsBuilder uriBuilder) {
		
		CategoriaDto creado = categoriaService.saveCategory(categoria);
		URI location = uriBuilder.path("/api/categorias/{id}")
				.buildAndExpand(creado.categoriaId())
				.toUri();
		return ResponseEntity.created(location).body(creado);
	}
    // ADMIN y LIBRARIAN puede actualizar categorias
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaDto> updateCategory(@PathVariable @Min(1) Long id, 
														@Valid	@RequestBody CategoriaDto categoria) {
		CategoriaDto actualizado = categoriaService.updateCategory(id, categoria);
		return ResponseEntity.ok(actualizado);
	}

    // Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoriaService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}
