package com.biblioteca.security.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name); // Busca roles por nombre (ROLE_USER, ROLE_BIBLIOTECARO, ROLE_ADMIN)
}
