package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.entity.Autor;

@SpringBootTest
class IAutorRepositoryTest {

	@Autowired
	IAutorRepository autorRepository;
	
	@Test
	public void guardarAutor() {
		Autor autor = new Autor(
				"Gabriel",      // nombre
                "García Márquez", // apellido
                "Colombiana",   // nacionalidad
                LocalDate.of(1927, 3, 6) // fechaNacimiento)
                ); 
		autorRepository.save(autor);
	}
	
	@Test
	public void findAllAutors() {
		List<Autor> autorList = autorRepository.findAll();
		autorList.forEach(System.out::println);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
