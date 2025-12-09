package com.example.demo.repositorios;

import com.example.demo.entidades.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad Cliente.
 * Proporciona operaciones CRUD básicas mediante CrudRepository.
 * 
 * @author NGINX
 * @version 1.0
 */

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}