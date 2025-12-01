package com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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

import com.biblioteca.service.usuario.IUsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Modulo Usuario")
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

    @Operation(summary = "Visualizar Usuarios",
            description = "Permite ver los usuarios que se encuentran en base de datos " )
    // ADMIN y LIBRARIAN pueden acceder
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDto>> findAllUsers(){
		return ResponseEntity.ok(usuarioService.findAllUsers());
	}

    @Operation(summary = "Buscar Usuario por Id",
            description = "Permite buscar algun usuario especifico por Id" )
    // ADMIN y LIBRARIAN puede visualizar usuarios
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDto> getUserById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.ok(usuarioService.findUserById(id));
	}

    @Operation(summary = "Guardar Usuario",
            description = "Permite guardar un usuario a los usuarios permitidos " )
    // Solo ADMIN puede crear usuarios de forma personalizada
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioDto> saveUser(@Valid @RequestBody RegistroUsuarioDto usuario,
													UriComponentsBuilder uriBuilder) {
		
		UsuarioDto creado = usuarioService.saveUser(usuario);

		URI location = uriBuilder.path("api/usuarios/{id}")
				.buildAndExpand(creado.usuarioId())
				.toUri();
		return ResponseEntity.created(location).body(creado);
	}

    @Operation(summary = "Actualizar Usuario",
            description = "Permite actualizar un usuario a los usuarios permitidos" )
    // Solo ADMIN puede editar datos de los usuarios
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/usuarios/{id}") //PathVariable recibe variable en URL
	public ResponseEntity<UsuarioDto> updateUser(@PathVariable Long id,
													@Valid @RequestBody UsuarioDto usuario) {
		UsuarioDto actualizado = usuarioService.updateUser(id, usuario);
		return ResponseEntity.ok(actualizado);
	}

    @Operation(summary = "Eliminar Usuario",
            description = "Permite eliminar un usuario a los usuarios permitidos " )
    // Solo ADMIN puede eliminar usuarios
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		usuarioService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
