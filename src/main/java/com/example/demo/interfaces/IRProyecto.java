package com.example.demo.interfaces;

import com.example.demo.entidades.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Proyecto.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRProyecto extends JpaRepository<Proyecto, Integer> {
}
