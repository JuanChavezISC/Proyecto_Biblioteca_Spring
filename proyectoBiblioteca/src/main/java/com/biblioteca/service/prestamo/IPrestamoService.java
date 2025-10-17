package com.biblioteca.service.prestamo;

import java.util.List;

import com.biblioteca.dto.PrestamoDto;

public interface IPrestamoService {

	List<PrestamoDto> findAllLoans();
	PrestamoDto findLoanById(Long id);
	PrestamoDto saveLoan(PrestamoDto prestamo);
	PrestamoDto updateLoan(Long id, PrestamoDto prestamo);	
	void deleteLoan(Long id);
	
}
