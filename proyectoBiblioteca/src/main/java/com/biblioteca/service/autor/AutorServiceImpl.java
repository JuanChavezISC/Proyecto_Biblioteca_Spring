package com.biblioteca.service.autor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.dto.AutorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		return autorMapper.toDto(aut, false);
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
		
		if (Objects.nonNull(autor.nombre()) && !"".equalsIgnoreCase(autor.nombre())) {
			autorDb.setNombre(autor.nombre());
		}
		if (Objects.nonNull(autor.apellido()) && !"".equalsIgnoreCase(autor.apellido())) {
			autorDb.setApellido(autor.apellido());
		}
		if (Objects.nonNull(autor.nacionalidad()) && !"".equalsIgnoreCase(autor.nacionalidad())) {
			autorDb.setNacionalidad(autor.nacionalidad());
		}
		if (Objects.nonNull(autor.fechaNacimiento())) {
			autorDb.setFechaNacimiento(autor.fechaNacimiento());
		}
		
		Autor actualizado = autorRepository.save(autorDb);
		
		return autorMapper.toDto(actualizado, false);
	}

	@Override
	public void deleteAutor(Long id) {
		autorRepository.deleteById(id);	
	}


}
