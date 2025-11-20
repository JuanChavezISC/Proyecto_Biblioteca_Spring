package com.biblioteca.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.dto.UsuarioDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.entity.Usuario;
import com.biblioteca.mapper.UsuarioMapper;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	private final UsuarioMapper usuarioMapper;
	
	private final IUsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioMapper usuarioMapper, IUsuarioRepository usuarioRepository) {
		super();
		this.usuarioMapper = usuarioMapper;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDto findUserById(Long id) {
		Usuario user = usuarioRepository.findById(id).orElseThrow(() -> 
		new RuntimeException("Usuario no encontrado con el id " + id));
		
		return usuarioMapper.toDto(user);
	}
	
	// Encontrar Todos los Usuarios
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDto> findAllUsers() {
		return usuarioRepository.findAll()
				.stream()
				.map(u -> usuarioMapper.toDto(u))
				.collect(Collectors.toList());
	}

	// Guardar todos los usuarios
	@Override
	public UsuarioDto saveUser(UsuarioDto usuario) {
		Usuario usuarioEntity = usuarioMapper.toEntity(usuario);
		
		Usuario guardado = usuarioRepository.save(usuarioEntity);
		
		return usuarioMapper.toDto(guardado);
	}

	// Actualizar Usuario
	@Override
	public UsuarioDto updateUser(Long id, UsuarioDto usuario) {
		
		Usuario usuarioDb = usuarioRepository.findById(id).get();
		if (Objects.nonNull(usuario.nombre()) && !"".equalsIgnoreCase(usuario.nombre())) {
			usuarioDb.setNombre(usuario.nombre());
		}
		if (Objects.nonNull(usuario.apellido()) && !"".equalsIgnoreCase(usuario.apellido())) {
			usuarioDb.setApellido(usuario.apellido());
		}
		if (Objects.nonNull(usuario.email()) && !"".equalsIgnoreCase(usuario.email())) {
			usuarioDb.setEmail(usuario.email());
		}
		
		Usuario actualizado = usuarioRepository.save(usuarioDb);
		return usuarioMapper.toDto(actualizado);
	}

	@Override
	public void deleteUser(Long id) {
		usuarioRepository.deleteById(id);
		
	}


	
}
