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

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.service.autor.IAutorService;

@RestController
@RequestMapping("/api")
public class AutorController {

	@Autowired
	IAutorService autorService;
	
	@GetMapping("/findAllAutors")
	public List<Autor> findAllAutors() {
		return autorService.findAllAutors();
	}
	
	@PostMapping("/saveAutor")
	public Autor saveAutor(@RequestBody AutorDto autor) {
		return autorService.saveAutor(autor);
	}
	
	@PutMapping("/updateAutor/{id}")
	public Autor updateAutor(@PathVariable Long id, @RequestBody AutorDto autor) {
		return autorService.updateAutor(id, autor);
	}
	
	@DeleteMapping("/deleteAutor/{id}")
	public String deleteAutor(@PathVariable Long id) {
		autorService.deleteAutor(id);
		return "Autor deleted successfully";
	}
}
