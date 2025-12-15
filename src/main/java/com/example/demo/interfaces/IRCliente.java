package com.example.demo.interfaces;

import com.example.demo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Cliente.
 * Proporciona operaciones CRUD básicas mediante JpaRepository.
 */
@Repository
public interface IRCliente extends JpaRepository<Cliente, Integer> {
}
