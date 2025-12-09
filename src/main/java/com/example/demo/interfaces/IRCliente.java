package com.example.demo.interfaces;

import com.example.demo.entidades.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Cliente.
 * Proporciona operaciones CRUD básicas mediante CrudRepository.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRCliente extends CrudRepository<Cliente, Integer> {
}