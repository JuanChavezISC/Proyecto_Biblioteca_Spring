package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.service.prestamo.IPrestamoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class PrestamoController {

	@Autowired
	IPrestamoService prestamoService;
	
	@GetMapping("/findAllLoans")
	public List<Prestamo> findAllLoans() {
		return prestamoService.findAllLoans();
	}
	
	@PostMapping("/saveLoan")
	public Prestamo saveLoan(@RequestBody PrestamoDto prestamo) {
		return prestamoService.saveLoan(prestamo);
	}
	
	@PutMapping("updateLoan/{id}")
	public Prestamo updateLoan(@PathVariable Long id, @RequestBody PrestamoDto prestamo) {
		return prestamoService.updateLoan(id, prestamo);
	}
	
	@DeleteMapping("/deleteLoan/{id}")
	public String deleteLoan(@PathVariable Long id) {
		prestamoService.deleteLoan(id);
		return "Loan deleted succesfully";
	}
}
