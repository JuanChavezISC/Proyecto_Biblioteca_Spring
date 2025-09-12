package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.entity.Usuario;
import com.biblioteca.service.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;
	
	
	@GetMapping("/findAllUsers")
	public List<Usuario> findAllUsers(){
		return usuarioService.findAllUsers();
	}
	
	@PostMapping("/saveUser")
	public Usuario saveUser(@RequestBody UsuarioDto usuario) {
		return usuarioService.saveUser(usuario);
	}
	
	@PutMapping("/updateUser/{id}") //PathVariable recibe variable en URL
	public Usuario updateUser(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
		return usuarioService.updateUser(id, usuario);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		usuarioService.deleteUser(id);
		return "Usuario deleted successfully ";
	}
}
