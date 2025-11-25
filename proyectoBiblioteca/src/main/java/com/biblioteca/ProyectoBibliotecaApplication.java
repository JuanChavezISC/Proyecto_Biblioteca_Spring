package com.biblioteca;

import com.biblioteca.security.role.Role;
import com.biblioteca.security.role.RoleRepository;
import com.biblioteca.security.user.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProyectoBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBibliotecaApplication.class, args);
	}

    // Bean de inicializacion
    @Bean
    CommandLineRunner seed(RoleRepository roleRepo, UserAccountRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            var rUser  = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_USER").description("Usuario").build()));
            var rAdmin = roleRepo.findByName("ROLE_ADMIN").orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_ADMIN").description("Administrador").build()));
            if (userRepo.findByUsername("admin").isEmpty()) { /* crear admin con ambos roles */ }
            if (userRepo.findByUsername("user").isEmpty())  { /* crear user con ROLE_USER */ }
        };
    }
}
