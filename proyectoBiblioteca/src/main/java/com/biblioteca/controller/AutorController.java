package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.service.autor.IAutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins= {"http://localhost:4200"})
public class AutorController {

	@Autowired
	IAutorService autorService;
	
	@GetMapping("/autores")
	@ResponseStatus (HttpStatus.OK)
	public List<Autor> autors() {
		return autorService.findAllAutors();
	}
	
	@GetMapping("/autores/{id}")
	public Autor getAutorById(@PathVariable Long id) {
		return autorService.getAutorById(id);
	}
	
	@PostMapping("/autores")
	public Autor saveAutor(@Valid  @RequestBody AutorDto autor,
										UriComponentsBuilder uriBuilder) {
		return autorService.saveAutor(autor);
	}
	
	@PutMapping("/autores/{id}")
	public Autor updateAutor(@PathVariable Long id, @RequestBody AutorDto autor) {
		return autorService.updateAutor(id, autor);
	}
	
	@DeleteMapping("/autores/{id}")
	public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
		autorService.deleteAutor(id);
		return ResponseEntity.noContent().build();
	}
}
