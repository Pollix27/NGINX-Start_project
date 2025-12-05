package com.example.demo.repositories;

import com.example.demo.models.ClienteModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Integer> {
    public abstract ArrayList<ClienteModel> findByPrioridad(Integer prioridad);

}