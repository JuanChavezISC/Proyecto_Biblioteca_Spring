package com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.service.prestamo.IPrestamoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

	@GetMapping("/prestamos")
	public ResponseEntity<List<PrestamoDto>> prestamos() {
		return ResponseEntity.ok(prestamoService.findAllLoans());
	}
	
	@GetMapping("prestamos/{id}")
	public ResponseEntity<PrestamoDto> findLoanById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(prestamoService.findLoanById(id));
	}
	
	@PostMapping("/prestamos")
	public ResponseEntity<PrestamoDto> saveLoan(@Valid @RequestBody PrestamoDto prestamo,
														UriComponentsBuilder uriBuilder) {
		PrestamoDto creado = prestamoService.saveLoan(prestamo);
		URI location = uriBuilder.path("api/prestamos/{id}")
				.buildAndExpand(creado.getPrestamoId())
				.toUri();
		
		return ResponseEntity.created(location).body(creado);
	}
	
	@PutMapping("prestamos/{id}")
	public ResponseEntity<PrestamoDto> updateLoan(@PathVariable Long id,
									@Valid @RequestBody PrestamoDto prestamo) {
		
		PrestamoDto actualizado = prestamoService.updateLoan(id, prestamo);
		
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/prestamos/{id}")
	public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
		prestamoService.deleteLoan(id);
		return ResponseEntity.noContent().build();
	}
}
