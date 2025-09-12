package com.biblioteca.service.autor;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.AutorDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.repository.IAutorRepository;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	IAutorRepository autorRepository;
	
	//Encontrar todos los autores
	@Override
	public List<Autor> findAllAutors() {
		return autorRepository.findAll();
	}

	// Guardar todos los autores
	@Override
	public Autor saveAutor(AutorDto autor) {

		Autor autorEntity = new Autor();
		autorEntity.setNombre(autor.getNombre());
		autorEntity.setApellido(autor.getApellido());
		autorEntity.setNacionalidad(autor.getNacionalidad());
		autorEntity.setFechaNacimiento(autor.getFechaNacimiento());
		return autorRepository.save(autorEntity);
	}

	@Override
	public Autor updateAutor(Long id, AutorDto autor) {
		Autor autorDb = autorRepository.findById(id).get();
		
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
		return autorRepository.save(autorDb);
	}

	@Override
	public void deleteAutor(Long id) {
		autorRepository.deleteById(id);	
	}

}
