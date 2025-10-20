package com.biblioteca.service.libro;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.dto.LibroDto;
import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Libro;
import com.biblioteca.mapper.LibroMapper;
import com.biblioteca.repository.IAutorRepository;
import com.biblioteca.repository.ICategoriaRepository;
import com.biblioteca.repository.ILibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {
	
	private final LibroMapper libroMapper;
	private final ILibroRepository libroRepository;
	private final IAutorRepository autorRepository;
	private final ICategoriaRepository categoriaRepository;

	
	public LibroServiceImpl(LibroMapper libroMapper, ILibroRepository libroRepository, IAutorRepository autorRepository,
			ICategoriaRepository categoriaRepository) {
		super();
		this.libroMapper = libroMapper;
		this.libroRepository = libroRepository;
		this.autorRepository = autorRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public LibroDto findBookById(Long id) {
		Libro lib = libroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado con el id " + id));
		
		return libroMapper.toDto(lib);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LibroDto> findAllBooks() {
		return libroRepository.findAll()
				.stream()
				.map(l -> libroMapper.toDto(l))
				.collect(Collectors.toList());
	}

	@Override
	public LibroDto saveBook(LibroDto libro) {
		
		Autor autorExistente = autorRepository.findById(libro.getAutorId())
				.orElseThrow(() -> new RuntimeException("autor no encontrado, ingrese un id diferente"));
		
		Categoria categoriaExistente = categoriaRepository.findById(libro.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("categoria no encontrada"));
		
		Libro libroEntity = libroMapper.toEntity(libro);
		
		// Asocia autor y categoria 
		libroEntity.setAutor(autorExistente);
		libroEntity.setCategoria(categoriaExistente);
		
		// Guardar en base de datos
		
		Libro guardado = libroRepository.save(libroEntity);
		
		return libroMapper.toDto(guardado);
	}

	@Override
	public LibroDto updateBook(Long id, LibroDto libro) {
		
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
		
		Libro actualizado = libroRepository.save(libroDb);
		
		return libroMapper.toDto(actualizado);
	}

	@Override
	public void deleteBook(Long id) {
		libroRepository.deleteById(id);
		System.out.println("Eliminado correctamente");
		
	}


}
