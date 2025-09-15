package com.biblioteca.service.libro;

import java.util.List;

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Libro;

public interface ILibroService {
	
	List<Libro> findAllBooks();
	
	Libro saveBook(LibroDto libro);
	
	Libro updateBook(Long id, LibroDto libro);
	
	void deleteBook(Long id);

}
