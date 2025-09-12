package com.biblioteca.service.categoria;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.CategoriaDto;
import com.biblioteca.entity.Categoria;
import com.biblioteca.repository.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	ICategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> findAllCategories() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria saveCategory(CategoriaDto categoria) {
		Categoria categoriaEntity = new Categoria();
		categoriaEntity.setDescripcion(categoria.getDescripcion());
		return categoriaRepository.save(categoriaEntity);
	}

	@Override
	public Categoria updateCategory(Long id, CategoriaDto categoria) {
		Categoria categoriaDb = categoriaRepository.findById(id).get();
		if (Objects.nonNull(categoria.getDescripcion()) && !"".equalsIgnoreCase(categoria.getDescripcion())) {
			categoriaDb.setDescripcion(categoria.getDescripcion());
		}
		return categoriaRepository.save(categoriaDb);
	}

	@Override
	public void deleteCategory(Long id) {
		categoriaRepository.deleteById(id);
	}

}
