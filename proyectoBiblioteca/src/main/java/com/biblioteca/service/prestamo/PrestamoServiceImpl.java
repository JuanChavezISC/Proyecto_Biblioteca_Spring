package com.biblioteca.service.prestamo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.dto.PrestamoDto;
import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.entity.Usuario;
import com.biblioteca.mapper.PrestamoMapper;
import com.biblioteca.repository.ILibroRepository;
import com.biblioteca.repository.IPrestamoRepository;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class PrestamoServiceImpl implements IPrestamoService{

	private final PrestamoMapper prestamoMapper;
	
	private final IPrestamoRepository prestamoRepository;
	
	private final ILibroRepository libroRepository;
	
	private final IUsuarioRepository usuarioRepository;
	
	public PrestamoServiceImpl(PrestamoMapper prestamoMapper, IPrestamoRepository prestamoRepository,
			ILibroRepository libroRepository, IUsuarioRepository usuarioRepository) {
		super();
		this.prestamoMapper = prestamoMapper;
		this.prestamoRepository = prestamoRepository;
		this.libroRepository = libroRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	// Encontrar los prestamos por Id
	@Override
	@Transactional(readOnly = true)
	public PrestamoDto findLoanById(Long id) {
		Prestamo pres = prestamoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prestamo no encontrado con el id " +id));
		return prestamoMapper.toDto(pres);
		
	}
	
	// Encontrar todos los prestamos
	@Override
	@Transactional(readOnly = true)
	public List<PrestamoDto> findAllLoans() {
		return prestamoRepository.findAll()
				.stream()
				.map(pr -> prestamoMapper.toDto(pr))
				.collect(Collectors.toList());
	}

	@Override
	public PrestamoDto saveLoan(PrestamoDto prestamo) {

		Usuario usuarioExistente = usuarioRepository.findById(prestamo.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("usuario no encontrado, ingrese un id diferente"));
		
		Libro libroExistente = libroRepository.findById(prestamo.getLibroId())
				.orElseThrow(() -> new RuntimeException("libro no encontrado, ingrese un id diferente"));
		
		Prestamo prestamoEntity = prestamoMapper.toEntity(prestamo);
		
		// Asociar usuario y libro
		
		prestamoEntity.setUsuario(usuarioExistente);
		prestamoEntity.setLibro(libroExistente);
		
		// Guardar en base de datos
		
		Prestamo guardado = prestamoRepository.save(prestamoEntity);
		
		return prestamoMapper.toDto(guardado);
	}

	@Override
	public PrestamoDto updateLoan(Long id, PrestamoDto prestamo) {
		
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
		
		Prestamo actualizado = prestamoRepository.save(prestamoDb);
		
		return prestamoMapper.toDto(actualizado);
	}

	@Override
	public void deleteLoan(Long id) {
		prestamoRepository.deleteById(id);
		System.out.println("Eliminado correctamente");
	}


}
