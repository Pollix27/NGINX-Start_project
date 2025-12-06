package com.example.demo.repositorios;

import com.example.demo.entidades.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
    ArrayList<Proyecto> findByClienteId(int clienteId);
}
