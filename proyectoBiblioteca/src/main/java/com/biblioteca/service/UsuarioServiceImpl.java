package com.biblioteca.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	IUsuarioRepository usuarioRepository;
	
	// Encontrar Todos los Usuarios
	@Override
	public List<Usuario> findAllUsers() {
		return usuarioRepository.findAll();
	}

	// Guardar todos los usuarios
	@Override
	public Usuario saveUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	// Actualizar Usuario
	@Override
	public Usuario updateUser(Long id, Usuario usuario) {
		
		Usuario usuarioDb = usuarioRepository.findById(id).get();
		if (Objects.nonNull(usuario.getNombre()) && !"".equalsIgnoreCase(usuario.getNombre())) {
			usuarioDb.setNombre(usuario.getNombre());
		}
		if (Objects.nonNull(usuario.getApellido()) && !"".equalsIgnoreCase(usuario.getApellido())) {
			usuarioDb.setApellido(usuario.getApellido());
		}
		if (Objects.nonNull(usuario.getEmail()) && !"".equalsIgnoreCase(usuario.getEmail())) {
			usuarioDb.setEmail(usuario.getEmail());
		}
		return usuarioRepository.save(usuarioDb);
	}

	@Override
	public void deleteUser(Long id) {
		usuarioRepository.deleteById(id);
		
	}

	
}
