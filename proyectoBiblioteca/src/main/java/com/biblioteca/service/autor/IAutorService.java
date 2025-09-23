package com.biblioteca.service.autor;

import java.util.List;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;

public interface IAutorService {

	Autor getAutorById(Long id);
	
	List<Autor> findAllAutors();
	
	Autor saveAutor(AutorDto autor);
	
	Autor updateAutor(Long id, AutorDto autor);
	
	void deleteAutor(Long id);
}
