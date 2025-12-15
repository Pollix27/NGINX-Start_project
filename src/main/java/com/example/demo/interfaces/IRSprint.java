package com.example.demo.interfaces;

import com.example.demo.entidades.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Sprint.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRSprint extends JpaRepository<Sprint, Integer> {
}
