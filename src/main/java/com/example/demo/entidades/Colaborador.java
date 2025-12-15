package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entidad que representa un colaborador en el sistema.
 * Un colaborador está asignado a un proyecto y opcionalmente a un sprint.
 */
@Data
@Getter
@Entity
@Table(name = "COLABORADORES")
public class Colaborador {
    
    /** Identificador único del colaborador */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador")
    private int idColaborador;
    
    /** Proyecto al que pertenece el colaborador */
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-colaboradores")
    private Proyecto proyecto;
    
    /** Sprint al que está asignado el colaborador (opcional) */
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    @JsonBackReference("sprint-colaboradores")
    private Sprint sprint;
    
    /** Nombre completo del colaborador */
    @Column(name = "nombre_colaborador", length = 100)
    private String nombreColaborador;
    
    /** Correo electrónico del colaborador */
    @Column(name = "email_colaborador", length = 100)
    private String emailColaborador;
    
    /** Rol del colaborador en el proyecto */
    @Column(name = "rol_colaborador", length = 50)
    private String rolColaborador;
    
    /** Tarifa por hora del colaborador */
    @Column(name = "tarifa_hora_programador", precision = 10, scale = 2)
    private BigDecimal tarifaHoraProgramador;
    
    /** Lista de presupuestos asociados al colaborador */
    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
    @JsonManagedReference("colaborador-presupuestos")
    private List<Presupuesto> presupuestos;

    public Colaborador() {}

}
