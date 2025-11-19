package com.biblioteca.service;

import java.util.List;

public interface IUsuarioService {
	
	List<UsuarioDto> findAllUsers();
	UsuarioDto findUserById(Long id);
	UsuarioDto saveUser(UsuarioDto usuario);
	UsuarioDto updateUser(Long id, UsuarioDto usuario);
	void deleteUser(Long id);
}
