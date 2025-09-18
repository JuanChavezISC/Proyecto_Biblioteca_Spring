package com.biblioteca.service.libro;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Libro;
import com.biblioteca.repository.IAutorRepository;
import com.biblioteca.repository.ICategoriaRepository;
import com.biblioteca.repository.ILibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	ILibroRepository libroRepository;
	
	@Autowired
	IAutorRepository autorRepository;
	
	@Autowired
	ICategoriaRepository categoriaRepository;

	@Override
	public List<Libro> findAllBooks() {
		return libroRepository.findAll();
	}

	@Override
	public Libro saveBook(LibroDto libro) {
		
		Autor autorExistente = autorRepository.findById(libro.getAutorId())
				.orElseThrow(() -> new RuntimeException("autor no encontrado, ingrese un id diferente"));
		
		Categoria categoriaExistente = categoriaRepository.findById(libro.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("categoria no encontrada"));
		
		Libro libroEntity = new Libro();
		libroEntity.setTitulo(libro.getTitulo());
		libroEntity.setIsbn(libro.getIsbn());
		libroEntity.setFechaPublicacion(libro.getFechaPublicacion());
		libroEntity.setAutor(autorExistente);
		libroEntity.setCategoria(categoriaExistente);
		return libroRepository.save(libroEntity);
	}

	@Override
	public Libro updateBook(Long id, LibroDto libro) {
		
		Libro libroDb = libroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));
		
		if (Objects.nonNull(libro.getTitulo()) && !"".equalsIgnoreCase(libro.getTitulo())) {
			libroDb.setTitulo(libro.getTitulo());
		}
		if (Objects.nonNull(libro.getIsbn()) && !"".equalsIgnoreCase(libro.getIsbn())) {
			libroDb.setIsbn(libro.getIsbn());
		}
		if (Objects.nonNull(libro.getFechaPublicacion())) {
			libroDb.setFechaPublicacion(libro.getFechaPublicacion());
		}
		
		// Actualizar Id
		if (Objects.nonNull(libro.getAutorId())) {
			Autor autorExistente = autorRepository.findById(libro.getAutorId())
					.orElseThrow(() -> new RuntimeException("autor no encontrado con id " + libro.getAutorId()));
			libroDb.setAutor(autorExistente);
		}
		if (Objects.nonNull(libro.getCategoriaId())) {
			Categoria categoriaExistente = categoriaRepository.findById(libro.getCategoriaId())
					.orElseThrow(() -> new RuntimeException("categoria no encontrada con id " + libro.getCategoriaId()));
			libroDb.setCategoria(categoriaExistente);
		}
		
		return libroRepository.save(libroDb);
	}

	@Override
	public void deleteBook(Long id) {
		libroRepository.deleteById(id);
		
	}

}
