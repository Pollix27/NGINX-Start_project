package com.example.demo.interfaces;

import com.example.demo.entidades.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Colaborador.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRColaborador extends JpaRepository<Colaborador, Integer> {
}
