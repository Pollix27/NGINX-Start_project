package com.example.demo.interfaces;

import com.example.demo.entidades.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Presupuesto.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRPresupuesto extends JpaRepository<Presupuesto, Integer> {
}
