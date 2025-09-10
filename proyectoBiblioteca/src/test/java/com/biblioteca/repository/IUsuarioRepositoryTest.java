package com.biblioteca.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.entity.Usuario;

@SpringBootTest
class IUsuarioRepositoryTest {
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Test
	public void guardarUsuario() {
		Usuario usuario = new Usuario(
					"Ursula", 
					"Jimenez", 
					"ursuJim@example.com"
					);
		usuarioRepository.save(usuario);
	}


	@Test
	public void findUsuarioByNombre() {
		Usuario usuario = usuarioRepository.findByNombre("Ursula").get();
		System.out.println("usuario = " + usuario);
	}

	@Test
	public void findAllUsers() {
		List<Usuario> usuarioList = usuarioRepository.findAll();
		usuarioList.forEach(System.out::println);
	}
	
}
