package com.biblioteca.repository;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.entity.Categoria;

@SpringBootTest
class ICategoriaRepositoryTest {
	
	@Autowired
	ICategoriaRepository categoriaRepository;

	@Test
	public void guardarCategoria() {
		Categoria categoria = new Categoria("Novela");
		categoriaRepository.save(categoria);
	}
	
	@Test
	public void findAllCategory() {
		List<Categoria> categoriaList = categoriaRepository.findAll();
		categoriaList.forEach(System.out::println);
	}

}
