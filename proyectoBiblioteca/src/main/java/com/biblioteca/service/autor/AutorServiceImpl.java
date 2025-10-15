package com.biblioteca.service.autor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.mapper.AutorMapper;
import com.biblioteca.repository.IAutorRepository;

@Service
public class AutorServiceImpl implements IAutorService{

    private final AutorMapper autorMapper;

	private final IAutorRepository autorRepository;
	
	
	
	public AutorServiceImpl(IAutorRepository autorRepository, AutorMapper autorMapper) {
		super();
		this.autorRepository = autorRepository;
		this.autorMapper = autorMapper;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public AutorDto getAutorById(Long id) {
		Autor aut = autorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Autor no encontrado con id " + id));
		
		AutorDto dto = new AutorDto();
		dto.setAutorId(aut.getAutorId());
		dto.setNombre(aut.getNombre());
		dto.setApellido(aut.getApellido());
		dto.setNacionalidad(aut.getNacionalidad());
		dto.setFechaNacimiento(aut.getFechaNacimiento());
		
		return dto;
	}
	
	//Encontrar todos los autores
	@Override
	@Transactional(readOnly = true)
	public List<AutorDto> findAllAutors() {
		return autorRepository.findAll()
				.stream()
				.map(a -> autorMapper.toDto(a, true))
				.collect(Collectors.toList());
	}

	// Guardar todos los autores
	@Override
	public AutorDto saveAutor(AutorDto dto) {

		Autor autorEntity = autorMapper.toEntity(dto);
		Autor guardado = autorRepository.save(autorEntity);
		
		AutorDto resultado = autorMapper.toDto(guardado, false);
		return resultado;
	}

	@Override
	public AutorDto updateAutor(Long id, AutorDto autor) {
		Autor autorDb = autorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("autor no encontrado con id " + id ));
		
		if (Objects.nonNull(autor.getNombre()) && !"".equalsIgnoreCase(autor.getNombre())) {
			autorDb.setNombre(autor.getNombre());
		}
		if (Objects.nonNull(autor.getApellido()) && !"".equalsIgnoreCase(autor.getApellido())) {
			autorDb.setApellido(autor.getApellido());
		}
		if (Objects.nonNull(autor.getNacionalidad()) && !"".equalsIgnoreCase(autor.getNacionalidad())) {
			autorDb.setNacionalidad(autor.getNacionalidad());
		}
		if (Objects.nonNull(autor.getFechaNacimiento())) {
			autorDb.setFechaNacimiento(autor.getFechaNacimiento());
		}
		
		Autor actualizado = autorRepository.save(autorDb);
		
		return autorMapper.toDto(actualizado, false);
	}

	@Override
	public void deleteAutor(Long id) {
		autorRepository.deleteById(id);	
	}


}
