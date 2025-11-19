package com.biblioteca.service.libro;

import java.util.List;

public interface ILibroService {
	
	List<LibroDto> findAllBooks();
	LibroDto findBookById(Long id);
	LibroDto saveBook(LibroDto libro);
	LibroDto updateBook(Long id, LibroDto libro);
	void deleteBook(Long id);

}
