package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Usuario;

public interface IUsuarioService {

	List<Usuario> findAllUsers();
	
	Usuario saveUser(Usuario usuario);
	
	Usuario updateUser(Long id, Usuario usuario);
	
	void deleteUser(Long id);
}
