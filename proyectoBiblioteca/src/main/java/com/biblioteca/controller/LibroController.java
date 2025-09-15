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

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Libro;
import com.biblioteca.service.libro.ILibroService;

@RestController
@RequestMapping("/api")
public class LibroController {

	@Autowired
	ILibroService libroService;
	
	@GetMapping("/findAllBooks")
	public List<Libro> findAllBooks() {
		return libroService.findAllBooks();
	}
	
	@PostMapping("/saveBook")
	public Libro saveBook(@RequestBody LibroDto libro) {
		return libroService.saveBook(libro);
	}
	
	@PutMapping("/updateBook/{id}")
	public Libro updateBook(@PathVariable Long id, @RequestBody LibroDto libro) {
		return libroService.updateBook(id, libro);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable Long id) {
		libroService.deleteBook(id);
		return "Libro deleted succesfully";
	}
}
