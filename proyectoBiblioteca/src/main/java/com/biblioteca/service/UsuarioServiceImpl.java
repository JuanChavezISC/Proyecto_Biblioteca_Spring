package com.biblioteca.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	IUsuarioRepository usuarioRepository;

	
	@Override
	public Usuario findUserById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> 
		new RuntimeException("Usuario no encontrado con el id " + id));
	}
	
	// Encontrar Todos los Usuarios
	@Override
	public List<Usuario> findAllUsers() {
		return usuarioRepository.findAll();
	}

	// Guardar todos los usuarios
	@Override
	public Usuario saveUser(UsuarioDto usuario) {
		Usuario usuarioEntity = new Usuario();
		usuarioEntity.setNombre(usuario.getNombre());
		usuarioEntity.setApellido(usuario.getApellido());
		usuarioEntity.setEmail(usuario.getEmail());
		return usuarioRepository.save(usuarioEntity);
	}

	// Actualizar Usuario
	@Override
	public Usuario updateUser(Long id, UsuarioDto usuario) {
		
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
