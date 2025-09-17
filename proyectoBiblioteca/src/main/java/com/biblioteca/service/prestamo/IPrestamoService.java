package com.biblioteca.service.prestamo;

import java.util.List;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.entity.Prestamo;

public interface IPrestamoService {

	List<Prestamo> findAllLoans();
	
	Prestamo saveLoan(PrestamoDto prestamo);
	
	Prestamo updateLoan(Long id, PrestamoDto prestamo);
	
	void deleteLoan(Long id);
	
}
