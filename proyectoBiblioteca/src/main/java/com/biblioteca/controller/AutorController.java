package com.biblioteca.controller;

import java.net.URI;
import java.util.List;


import com.biblioteca.dto.AutorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import com.biblioteca.service.autor.IAutorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
@Validated
public class AutorController {

	
	private final IAutorService autorService;
	
	public AutorController(IAutorService autorService) {
		super();
		this.autorService = autorService;
	}

    @Operation(summary = "Visualizar Autores",
            description = "Permite visualizar todos los autores que se encuentren en base de datos " )
    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/autores")
	public ResponseEntity<List<AutorDto>> autors() {
		return ResponseEntity.ok(autorService.findAllAutors());		
	}

    @Operation(summary = "Buscar Autor por Id",
            description = "Permite buscar algun autor especifico empleando su Id")
    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/autores/{id}")
	public ResponseEntity<AutorDto> getAutorById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(autorService.getAutorById(id));
	}

    @Operation(summary = "Guardar Autor",
            description = "Permite guardar autores a los usuarios permitidos" )
	// ADMIN y LIBRARIAN puede crear autores
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PostMapping("/autores")
	public ResponseEntity<AutorDto>saveAutor(@Valid  @RequestBody AutorDto autor,
										UriComponentsBuilder uriBuilder) {
		
		AutorDto creado = autorService.saveAutor(autor);
		URI location = uriBuilder.path("/api/autores/{id}")
				.buildAndExpand(creado.autorId())
				.toUri();
		return ResponseEntity.created(location).body(creado);
	}

    @Operation(summary = "Actualizar Autor",
            description = "Permite actualizar autores a los usuarios permitidos" )
	// ADMIN y LIBRARIAN puede actualizar autores
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PutMapping("/autores/{id}")
	public ResponseEntity<AutorDto> updateAutor(@PathVariable @Min(1) Long id,
													@Valid @RequestBody AutorDto autor) {
		AutorDto actualizado = autorService.updateAutor(id, autor); 
		return ResponseEntity.ok(actualizado);
	}

    @Operation(summary = "Eliminar Autor",
            description = "Permite eliminar autores a los usuarios permitidos" )
    // Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/autores/{id}")
	public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
		autorService.deleteAutor(id);
		return ResponseEntity.noContent().build();
	}
}
