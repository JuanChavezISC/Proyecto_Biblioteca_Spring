package com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.biblioteca.service.IUsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
@Validated
public class UsuarioController {

	private final IUsuarioService usuarioService;
	
	public UsuarioController(IUsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDto>> findAllUsers(){
		return ResponseEntity.ok(usuarioService.findAllUsers());
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDto> getUserById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(usuarioService.findUserById(id));
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioDto> saveUser(@Valid @RequestBody RegistroUsuarioDto usuario,
													UriComponentsBuilder uriBuilder) {
		
		UsuarioDto creado = usuarioService.saveUser(usuario);

		URI location = uriBuilder.path("api/usuarios/{id}")
				.buildAndExpand(creado.usuarioId())
				.toUri();
		return ResponseEntity.created(location).body(creado);
	}
	
	@PutMapping("/usuarios/{id}") //PathVariable recibe variable en URL
	public ResponseEntity<UsuarioDto> updateUser(@PathVariable Long id,
													@Valid @RequestBody UsuarioDto usuario) {
		UsuarioDto actualizado = usuarioService.updateUser(id, usuario);
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		usuarioService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
