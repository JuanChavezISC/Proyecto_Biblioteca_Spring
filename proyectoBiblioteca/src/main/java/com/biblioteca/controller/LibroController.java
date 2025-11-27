package com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import com.biblioteca.dto.LibroDto;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.service.libro.ILibroService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
@Validated
public class LibroController {

	private final ILibroService libroService;
	
	public LibroController(ILibroService libroService) {
		super();
		this.libroService = libroService;
	}

    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/libros")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<LibroDto>> libros() {
		return ResponseEntity.ok(libroService.findAllBooks());
	}

    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroDto> getBookById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(libroService.findBookById(id));
	}

    // ADMIN y LIBRARIAN puede crear libros
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PostMapping("/libros")
	public ResponseEntity<LibroDto> saveBook(@Valid @RequestBody LibroDto libro,
													UriComponentsBuilder uriBuilder) {
		
		LibroDto creado = libroService.saveBook(libro);
		URI location = uriBuilder.path("api/libros/{id}")
				.buildAndExpand(creado.libroId())
				.toUri();
		return ResponseEntity.created(location).body(creado);
	}

    // ADMIN y LIBRARIAN puede actualizar libros
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PutMapping("/libros/{id}")
	public ResponseEntity<LibroDto> updateBook(@PathVariable Long id,
												@Valid @RequestBody LibroDto libro) {
		
		LibroDto actualizado = libroService.updateBook(id, libro);
		return ResponseEntity.ok(actualizado);
	}

    // Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		libroService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
}
