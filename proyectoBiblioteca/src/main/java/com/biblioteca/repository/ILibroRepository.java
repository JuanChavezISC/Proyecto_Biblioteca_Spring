package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Libro;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long>{

}
