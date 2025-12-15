package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Entidad que representa un requisito en el sistema.
 * Un requisito pertenece a un proyecto y opcionalmente a un sprint.
 */
@Data
@Entity
@Table(name = "REQUISITOS")
public class Requisito {
    
    /** Identificador único del requisito */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisitos")
    private int idRequisitos;
    
    /** Proyecto al que pertenece el requisito */
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-requisitos")
    private Proyecto proyecto;
    
    /** Sprint al que está asignado el requisito (opcional) */
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    @JsonBackReference("sprint-requisitos")
    private Sprint sprint;
    
    /** Descripción detallada del requisito */
    @Column(name = "descripcion_requisitos", columnDefinition = "TEXT")
    private String descripcionRequisitos;
    
    /** Nivel de prioridad del requisito */
    @Column(name = "prioridad_requisitos", length = 50)
    private String prioridadRequisitos;
    
    /** Lista de presupuestos asociados al requisito */
    @OneToMany(mappedBy = "requisito", cascade = CascadeType.ALL)
    @JsonManagedReference("requisito-presupuestos")
    private List<Presupuesto> presupuestos;

    public Requisito() {}

}
