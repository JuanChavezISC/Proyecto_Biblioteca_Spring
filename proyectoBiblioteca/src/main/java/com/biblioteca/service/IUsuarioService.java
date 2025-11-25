package com.biblioteca.service;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
	
	List<UsuarioDto> findAllUsers();
	UsuarioDto findUserById(Long id);
	UsuarioDto saveUser(RegistroUsuarioDto usuario);
	UsuarioDto updateUser(Long id, UsuarioDto usuario);
	void deleteUser(Long id);
}
