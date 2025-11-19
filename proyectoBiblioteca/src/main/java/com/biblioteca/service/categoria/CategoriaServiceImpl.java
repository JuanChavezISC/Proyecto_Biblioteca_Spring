package com.biblioteca.service.categoria;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.biblioteca.entity.Categoria;
import com.biblioteca.mapper.CategoriaMapper;
import com.biblioteca.repository.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {


	private final CategoriaMapper categoriaMapper;
	private final ICategoriaRepository categoriaRepository;
	
	
	public CategoriaServiceImpl(CategoriaMapper categoriaMapper, ICategoriaRepository categoriaRepository) {
		super();
		this.categoriaMapper = categoriaMapper;
		this.categoriaRepository = categoriaRepository;
	}

	
	// Encontrar Categoria por Id
	@Override
	@Transactional(readOnly = true)
	public CategoriaDto findCategoriaById(Long id) {
		Categoria cat = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada con id " + id));
		
		return categoriaMapper.toDto(cat);
	}
	
	// Encontrar todas las categorias
	@Override
	@Transactional(readOnly = true)
	public List<CategoriaDto> findAllCategories() {
		return categoriaRepository.findAll()
				.stream()
				.map(c -> categoriaMapper.toDto(c))
				.collect(Collectors.toList());
	}

	// Guardar categoria
	@Override
	public CategoriaDto saveCategory(CategoriaDto categoria) {
		Categoria categoriaEntity = categoriaMapper.toEntity(categoria);
		Categoria guardado = categoriaRepository.save(categoriaEntity);
		
		CategoriaDto resultado = categoriaMapper.toDto(guardado);
		return resultado;
	}

	@Override
	public CategoriaDto updateCategory(Long id, CategoriaDto categoria) {
		Categoria categoriaDb = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada con el id: " + id));
		if (Objects.nonNull(categoria.getDescripcion()) && !"".equalsIgnoreCase(categoria.getDescripcion())) {
			categoriaDb.setDescripcion(categoria.getDescripcion());
		}
		
		Categoria actualizado = categoriaRepository.save(categoriaDb);
		
		return categoriaMapper.toDto(actualizado);
	}

	@Override
	public void deleteCategory(Long id) {
		categoriaRepository.deleteById(id);
	}


}
