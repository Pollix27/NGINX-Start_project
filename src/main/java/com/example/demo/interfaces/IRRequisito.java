package com.example.demo.interfaces;

import com.example.demo.entidades.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Requisito.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRRequisito extends JpaRepository<Requisito, Integer> {
}
