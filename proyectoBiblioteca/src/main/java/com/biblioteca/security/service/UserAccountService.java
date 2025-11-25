package com.biblioteca.security.service;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.entity.Usuario;
import com.biblioteca.security.dto.RequestsResponses;
import com.biblioteca.security.role.Role;
import com.biblioteca.security.role.RoleRepository;
import com.biblioteca.security.user.UserAccount;
import com.biblioteca.security.user.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service @RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserAccount register(RegistroUsuarioDto dto, Usuario usuario){

        // Validar Username unico
        userRepo.findByUsername(dto.username()).ifPresent(u -> {
            throw new IllegalArgumentException("Username en uso");
        });
        // Validar email unico
        userRepo.findByEmail(dto.email()).ifPresent(u -> {
            throw new IllegalArgumentException("Email en uso");
        });

        // Asegurar que exista ROLE_USER
        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(Role.builder()
                        .name("ROLE_USER")
                        .description("Usuario basico")
                        .build()));

        UserAccount user = UserAccount.builder()
                .username(dto.username())
                .email(dto.email())
                .passwordHash(encoder.encode(dto.password()))
                .roles(Set.of(userRole))
                .usuario(usuario)
                .build();
        return userRepo.save(user);
    }
}
