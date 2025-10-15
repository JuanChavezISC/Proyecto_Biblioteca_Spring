package com.biblioteca.service.autor;

import java.util.List;

import com.biblioteca.dto.AutorDto;

public interface IAutorService {

	List<AutorDto> findAllAutors();
	AutorDto getAutorById(Long id);
	AutorDto saveAutor(AutorDto autor);
	AutorDto updateAutor(Long id, AutorDto autor);
	void deleteAutor(Long id);
}
