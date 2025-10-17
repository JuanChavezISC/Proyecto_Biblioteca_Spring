package com.biblioteca.service.libro;

import java.util.List;

import com.biblioteca.dto.LibroDto;

public interface ILibroService {
	
	List<LibroDto> findAllBooks();
	LibroDto findBookById(Long id);
	LibroDto saveBook(LibroDto libro);
	LibroDto updateBook(Long id, LibroDto libro);
	void deleteBook(Long id);

}
