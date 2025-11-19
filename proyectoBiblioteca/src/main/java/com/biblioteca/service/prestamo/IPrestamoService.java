package com.biblioteca.service.prestamo;

import com.biblioteca.dto.PrestamoDto;

import java.util.List;

public interface IPrestamoService {

	List<PrestamoDto> findAllLoans();
	PrestamoDto findLoanById(Long id);
	PrestamoDto saveLoan(PrestamoDto prestamo);
	PrestamoDto updateLoan(Long id, PrestamoDto prestamo);	
	void deleteLoan(Long id);
	
}
