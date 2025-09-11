package com.biblioteca.service;

import java.util.List;

import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.entity.Usuario;

public interface IUsuarioService {

	List<Usuario> findAllUsers();
	
	Usuario saveUser(UsuarioDto usuario);
	
	Usuario updateUser(Long id, UsuarioDto usuario);
	
	void deleteUser(Long id);
}
