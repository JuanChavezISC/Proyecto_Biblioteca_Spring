package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entity.Prestamo;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Long>{

}
