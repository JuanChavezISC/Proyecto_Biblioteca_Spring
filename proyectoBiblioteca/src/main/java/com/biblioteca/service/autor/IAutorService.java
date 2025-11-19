package com.biblioteca.service.autor;

import com.biblioteca.dto.AutorDto;

import java.util.List;

public interface IAutorService {

	List<AutorDto> findAllAutors();
	AutorDto getAutorById(Long id);
	AutorDto saveAutor(AutorDto autor);
	AutorDto updateAutor(Long id, AutorDto autor);
	void deleteAutor(Long id);
}
