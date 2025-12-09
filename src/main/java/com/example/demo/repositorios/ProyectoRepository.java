package com.example.demo.repositorios;

import com.example.demo.entidades.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad Proyecto.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
    /**
     * Busca todos los proyectos asociados a un cliente específico.
     * @param idCliente ID del cliente
     * @return Lista de proyectos del cliente
     */
    ArrayList<Proyecto> findByClienteIdCliente(int idCliente);
}
