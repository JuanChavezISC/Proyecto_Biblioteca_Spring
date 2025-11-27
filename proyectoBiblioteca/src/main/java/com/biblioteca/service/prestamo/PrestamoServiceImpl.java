package com.biblioteca.service.prestamo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.dto.PrestamoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
				.map(prestamoMapper::toDto)
				.collect(Collectors.toList());
	}

    // Guardar Prestamos
	@Override
    @Transactional
	public PrestamoDto saveLoan(PrestamoDto prestamo) {

		Usuario usuarioExistente = usuarioRepository.findById(prestamo.usuarioId())
				.orElseThrow(() -> new RuntimeException("usuario no encontrado, ingrese un id diferente"));
		
		Libro libroExistente = libroRepository.findById(prestamo.libroId())
				.orElseThrow(() -> new RuntimeException("libro no encontrado, ingrese un id diferente"));

        // Crear el prestamo sin usuario ni libro
		Prestamo prestamoEntity = prestamoMapper.toEntity(prestamo);
		
		// Asignar entidades gestionadas por JPA
		
		prestamoEntity.setUsuario(usuarioExistente);
        prestamoEntity.setLibro(libroExistente);
		
		// Guardar en base de datos
		
		Prestamo guardado = prestamoRepository.save(prestamoEntity);
		
		return prestamoMapper.toDto(guardado);
	}

	@Override
    @Transactional
	public PrestamoDto updateLoan(Long id, PrestamoDto prestamo) {
		
		Prestamo prestamoDb = prestamoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id " + id));

		if (Objects.nonNull(prestamo.libroId())) {
			Libro libroExistente = libroRepository.findById(prestamo.libroId())
					.orElseThrow(() -> new RuntimeException("libro no encontrado con id " + prestamo.libroId()));
			prestamoDb.setLibro(libroExistente);
		}
		if (Objects.nonNull(prestamo.usuarioId())) {
			Usuario usuarioExistente = usuarioRepository.findById(prestamo.usuarioId())
					.orElseThrow(() -> new RuntimeException("usuario no encontrado con id " + prestamo.usuarioId()));
			prestamoDb.setUsuario(usuarioExistente);
		}
		
		if (Objects.nonNull(prestamo.fechaPrestamo())) {
			prestamoDb.setFechaPrestamo(prestamo.fechaPrestamo());
		}
		
		if (Objects.nonNull(prestamo.fechaDevolucion())) {
			prestamoDb.setFechaDevolucion(prestamo.fechaDevolucion());
		}

		if (Objects.nonNull(prestamo.devuelto())) {
			prestamoDb.setDevuelto(prestamo.devuelto());
		}
		
		Prestamo actualizado = prestamoRepository.save(prestamoDb);
		
		return prestamoMapper.toDto(actualizado);
	}

	@Override
    @Transactional
	public void deleteLoan(Long id) {
		prestamoRepository.deleteById(id);
	}


}
