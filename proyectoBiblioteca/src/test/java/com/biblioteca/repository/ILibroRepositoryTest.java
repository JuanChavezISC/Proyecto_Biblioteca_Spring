package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Libro;

@SpringBootTest
class ILibroRepositoryTest {

	@Autowired
	ILibroRepository libroRepository;
	
	@Autowired
	IAutorRepository autorRepository;
	
	@Autowired
	ICategoriaRepository categoriaRepository;
	
	@Test
	public void guardarLibro() {
		
		Long autorId = 1L;
		Long categoriaId = 1L;
		
		Autor autorExistente = autorRepository.findById(autorId)
				.orElseThrow(() -> new RuntimeException("Autor no Encontrado"));
		
		Categoria categoriaExistente = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("categoria no encontrada"));
		
		Libro libro = new Libro();
		libro.setTitulo("Cien Años de Soledad");
		libro.setIsbn("978-0307474728");
		libro.setFechaPublicacion(LocalDate.of(1967, 6, 5));
		libro.setAutor(autorExistente);
		libro.setCategoria(categoriaExistente);
		
		libroRepository.save(libro);
		
		System.out.println("Libro Guardado con Autor y Categoria Existente: " + libro);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
