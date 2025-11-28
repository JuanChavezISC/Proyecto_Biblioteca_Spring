package com.biblioteca;

import com.biblioteca.security.role.Role;
import com.biblioteca.security.role.RoleRepository;
import com.biblioteca.security.user.UserAccountRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@SpringBootApplication
public class ProyectoBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBibliotecaApplication.class, args);
	}

    // Bean de inicializacion
    @Bean
    CommandLineRunner seed(RoleRepository roleRepo, UserAccountRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            var rUser  = roleRepo.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_USER").description("Usuario").build()));

            var rAdmin = roleRepo.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_ADMIN").description("Administrador").build()));

            var rLibrarian = roleRepo.findByName("ROLE_LIBRARIAN")
                    .orElseGet(() -> roleRepo.save(Role.builder().name("ROLE_LIBRARIAN").description("Bibliotecario").build()));

            if (userRepo.findByUsername("admin").isEmpty()) { /* crear admin con ambos roles */ }
            if (userRepo.findByUsername("user").isEmpty())  { /* crear user con ROLE_USER */ }

            // Crear ADMIN inicial
            if (userRepo.findByUsername("admin").isEmpty()) {
                var admin = com.biblioteca.security.user.UserAccount.builder()
                        .username("admin")
                        .email("admin@mail.com")
                        .passwordHash(encoder.encode("admin123"))
                        .roles(Set.of(rAdmin, rLibrarian)) // O solo rAdmin si prefieres
                        .build();

                userRepo.save(admin);
                System.out.println("ADMIN creado correctamente");
            }

            // Crear usuario normal
            if (userRepo.findByUsername("user").isEmpty()) {
                var user = com.biblioteca.security.user.UserAccount.builder()
                        .username("user")
                        .email("user@mail.com")
                        .passwordHash(encoder.encode("user123"))
                        .roles(Set.of(rUser))
                        .build();

                userRepo.save(user);
                System.out.println("USER creado correctamente");
            }

            // Crear BIBLIOTECARIO
            if (userRepo.findByUsername("bibliotecario").isEmpty()) {
                var librarian = com.biblioteca.security.user.UserAccount.builder()
                        .username("bibliotecario")
                        .email("biblio@mail.com")
                        .passwordHash(encoder.encode("biblio123"))
                        .roles(Set.of(rLibrarian))
                        .build();

                userRepo.save(librarian);
                System.out.println("BIBLIOTECARIO creado correctamente");
            }
        };
    }
}
