package com.biblioteca.security.service;

import com.biblioteca.security.role.Role;
import com.biblioteca.security.role.RoleRepository;
import com.biblioteca.security.user.UserAccount;
import com.biblioteca.security.user.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AdminUserService {

    private final UserAccountRepository userRepo;
    private final RoleRepository roleRepo;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserAccount> listAll() {
        return userRepo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserAccount assignRole(String username, String roleName) {
        String normalized = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;

        UserAccount user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));

        Role role = roleRepo.findByName(normalized)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + normalized));

        // Eliminar roles previos para que no se dupliquen

        user.getRoles().clear();
        // Asignar nuevo rol
        user.getRoles().add(role);

        return userRepo.save(user);
    }
}
