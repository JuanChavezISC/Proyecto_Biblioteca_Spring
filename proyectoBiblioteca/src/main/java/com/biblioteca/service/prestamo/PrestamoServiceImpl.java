package com.biblioteca.service.prestamo;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.ILibroRepository;
import com.biblioteca.repository.IPrestamoRepository;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class PrestamoServiceImpl implements IPrestamoService{

	@Autowired
	IPrestamoRepository prestamoRepository;
	
	@Autowired
	ILibroRepository libroRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Override
	public Prestamo findLoanById(Long id) {
		return prestamoRepository.findById(id).orElseThrow(() -> 
		new RuntimeException("Prestamo no encontrado con el id " +id));
	}
	
	
	@Override
	public List<Prestamo> findAllLoans() {
		return prestamoRepository.findAll();
	}

	@Override
	public Prestamo saveLoan(PrestamoDto prestamo) {

		Usuario usuarioExistente = usuarioRepository.findById(prestamo.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("usuario no encontrado"));
		
		Libro libroExistente = libroRepository.findById(prestamo.getLibroId())
				.orElseThrow(() -> new RuntimeException("libro no encontrado"));
		
		Prestamo prestamoEntity = new Prestamo();
		prestamoEntity.setLibro(libroExistente);
		prestamoEntity.setUsuario(usuarioExistente);
		prestamoEntity.setFechaPrestamo(prestamo.getFechaPrestamo());
		prestamoEntity.setFechaDevolucion(prestamo.getFechaDevolucion());
		prestamoEntity.setDevuelto(false);
		
		return prestamoRepository.save(prestamoEntity);
	}

	@Override
	public Prestamo updateLoan(Long id, PrestamoDto prestamo) {
		
		Prestamo prestamoDb = prestamoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id " + id));
		
		if (Objects.nonNull(prestamo.getLibroId())) {
			Libro libroExistente = libroRepository.findById(prestamo.getLibroId())
					.orElseThrow(() -> new RuntimeException("libro no encontrado con id " + prestamo.getLibroId()));
			prestamoDb.setLibro(libroExistente);
		}
		if (Objects.nonNull(prestamo.getUsuarioId())) {
			Usuario usuarioExistente = usuarioRepository.findById(prestamo.getUsuarioId())
					.orElseThrow(() -> new RuntimeException("usuario no encontrado con id " + prestamo.getUsuarioId()));
			prestamoDb.setUsuario(usuarioExistente);
		}
		
		if (Objects.nonNull(prestamo.getFechaPrestamo())) {
			prestamoDb.setFechaPrestamo(prestamo.getFechaPrestamo());
		}
		
		if (Objects.nonNull(prestamo.getFechaDevolucion())) {
			prestamoDb.setFechaDevolucion(prestamo.getFechaDevolucion());
		}

		if (Objects.nonNull(prestamo.getDevuelto())) {
			prestamoDb.setDevuelto(prestamo.getDevuelto());
		}
		
		
		
		return prestamoRepository.save(prestamoDb);
	}

	@Override
	public void deleteLoan(Long id) {
		prestamoRepository.deleteById(id);
	}


}
