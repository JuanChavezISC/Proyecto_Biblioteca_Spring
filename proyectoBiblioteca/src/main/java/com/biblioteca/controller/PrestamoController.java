package com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import com.biblioteca.dto.PrestamoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.service.prestamo.IPrestamoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Modulo Prestamo")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
@Validated
public class PrestamoController {

	private final IPrestamoService prestamoService;
	
	public PrestamoController(IPrestamoService prestamoService) {
		super();
		this.prestamoService = prestamoService;
	}

    @Operation(summary = "Visualizar prestamos",
            description = "Permite visualizar los prestamos a los usuarios permitidos " )
    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("/prestamos")
	public ResponseEntity<List<PrestamoDto>> prestamos() {
		return ResponseEntity.ok(prestamoService.findAllLoans());
	}

    @Operation(summary = "Buscar prestamos por Id",
            description = "Permite buscar  un prestamo especifico por Id" )
    // Pueden acceder todos los usuarios autenticados
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LIBRARIAN')")
	@GetMapping("prestamos/{id}")
	public ResponseEntity<PrestamoDto> findLoanById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(prestamoService.findLoanById(id));
	}

    @Operation(summary = "Guardar Prestamo",
            description = "Permite guardar un prestamo a los usuarios permitidos " )
    // ADMIN y LIBRARIAN puede crear prestamos
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PostMapping("/prestamos")
	public ResponseEntity<PrestamoDto> saveLoan(@Valid @RequestBody PrestamoDto prestamo,
														UriComponentsBuilder uriBuilder) {
		PrestamoDto creado = prestamoService.saveLoan(prestamo);
		URI location = uriBuilder.path("api/prestamos/{id}")
				.buildAndExpand(creado.prestamoId())
				.toUri();
		
		return ResponseEntity.created(location).body(creado);
	}

    @Operation(summary = "Actualizar Prestamo",
            description = "Permite actualizar un prestamo a los usuarios permitidos " )
    // ADMIN y LIBRARIAN puede modificar prestamos
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@PutMapping("prestamos/{id}")
	public ResponseEntity<PrestamoDto> updateLoan(@PathVariable Long id,
									@Valid @RequestBody PrestamoDto prestamo) {
		
		PrestamoDto actualizado = prestamoService.updateLoan(id, prestamo);
		
		return ResponseEntity.ok(actualizado);
	}

    @Operation(summary = "Eliminar Prestamo",
            description = "Permite eliminar un prestamo a los usuarios permitidos " )
    // Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/prestamos/{id}")
	public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
		prestamoService.deleteLoan(id);
		return ResponseEntity.noContent().build();
	}
}
