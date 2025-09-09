package com.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Usuario;


@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNombre(String nombre);
}
