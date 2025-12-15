package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa un sprint en el sistema.
 * Un sprint pertenece a un proyecto y contiene requisitos y colaboradores asignados.
 */
@Data
@Getter
@Entity
@Table(name = "SPRINTS")
public class Sprint {
    
    /** Identificador único del sprint */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sprint")
    private int idSprint;
    
    /** Proyecto al que pertenece el sprint */
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-sprints")
    private Proyecto proyecto;
    
    /** Nombre del sprint */
    @Column(name = "nombre_sprint", length = 100)
    private String nombreSprint;
    
    /** Estado actual del sprint */
    @Column(name = "estado_sprint", length = 50)
    private String estadoSprint;
    
    /** Fecha de inicio del sprint */
    @Column(name = "fecha_inicio_sprint")
    private LocalDate fechaInicioSprint;
    
    /** Fecha de finalización del sprint */
    @Column(name = "fecha_fin_sprint")
    private LocalDate fechaFinSprint;
    
    /** Lista de requisitos asignados al sprint */
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    @JsonManagedReference("sprint-requisitos")
    private List<Requisito> requisitos;
    
    /** Lista de colaboradores asignados al sprint */
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    @JsonManagedReference("sprint-colaboradores")
    private List<Colaborador> colaboradores;

    public Sprint() {}

}
