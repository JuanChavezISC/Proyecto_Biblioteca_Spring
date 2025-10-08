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

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Libro;
import com.biblioteca.service.libro.ILibroService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class LibroController {

	@Autowired
	ILibroService libroService;
	
	@GetMapping("/libros")
	@ResponseStatus (HttpStatus.OK)
	public List<Libro> libros() {
		return libroService.findAllBooks();
	}
	
	@GetMapping("/libros/{id}")
	public Libro findBookById(@PathVariable Long id) {
		return libroService.findBookById(id);
	}
	
	
	@PostMapping("/libros")
	public Libro saveBook(@RequestBody LibroDto libro) {
		return libroService.saveBook(libro);
	}
	
	@PutMapping("/libros/{id}")
	public Libro updateBook(@PathVariable Long id, @RequestBody LibroDto libro) {
		return libroService.updateBook(id, libro);
	}
	
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		libroService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
}
