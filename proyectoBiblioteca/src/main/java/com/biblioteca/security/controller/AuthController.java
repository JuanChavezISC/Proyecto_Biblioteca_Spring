package com.biblioteca.security.controller;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.security.dto.RequestsResponses;
import com.biblioteca.security.service.AuthService;
import com.biblioteca.security.service.UserAccountService;
import com.biblioteca.service.usuario.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Modulo Autorizacion")
@RestController
@RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final IUsuarioService usuarioService;
    private final UserAccountService userService;
    private final AuthService authService;

    @Operation(summary = "Registrar Usuario",
            description = "Permite registrar un nuevo usuario que tendra el rol USER por defecto " )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistroUsuarioDto
                                              request) {
        UsuarioDto nuevoUsuario = usuarioService.saveUser(request);
        return ResponseEntity.ok(Map.of(
                "message", "usuario registrado correctamente",
                "username", request.username(),
                "email", request.email()
        ));
    }

    @Operation(summary = "Iniciar Sesion",
            description = "Permite iniciari sesion a un usuario y generarle un token JWT" )
    @PostMapping("/login")
    public ResponseEntity<RequestsResponses.AuthResponse> login(@Valid @RequestBody RequestsResponses.LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }
}
