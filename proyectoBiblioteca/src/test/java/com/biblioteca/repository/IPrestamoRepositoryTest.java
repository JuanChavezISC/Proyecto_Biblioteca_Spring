package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.entity.Usuario;

@SpringBootTest
class IPrestamoRepositoryTest {

	@Autowired
	IPrestamoRepository prestamoRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	ILibroRepository libroRepository;
	
	@Test
	public void guardarPrestamo() {
		Long usuarioId = 4L;
		Long libroId = 1L;
		
		Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		Libro libroExistente = libroRepository.findById(libroId)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado"));
		
		Prestamo prestamo = new Prestamo();
		prestamo.setLibro(libroExistente);
		prestamo.setUsuario(usuarioExistente);
		prestamo.setFechaPrestamo(LocalDate.now());
		prestamo.setFechaDevolucion(LocalDate.now().plusDays(15));
		prestamo.setDevuelto(false);
		
		prestamoRepository.save(prestamo);
		
		System.out.println("Prestamo con Usuario y Libro Existentes Guardado: " + prestamo);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
