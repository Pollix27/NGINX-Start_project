package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa un proyecto en el sistema.
 * Un proyecto pertenece a un cliente y contiene requisitos, sprints, colaboradores y presupuestos.
 */
@Data
@Entity
@Table(name = "PROYECTOS")
public class Proyecto {
    
    /** Identificador único del proyecto */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int idProyecto;
    
    /** Cliente propietario del proyecto */
    @ManyToOne
    @JoinColumn(name = "id_cliente_fk", nullable = false)
    @JsonBackReference
    private Cliente cliente;
    
    /** Nombre del proyecto */
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    @Column(name = "nombre_proyecto", nullable = false, length = 200)
    private String nombreProyecto;
    
    /** Descripción detallada del proyecto */
    @Column(name = "descripcion_proyecto", columnDefinition = "TEXT")
    private String descripcionProyecto;
    
    /** Nivel de complejidad del proyecto */
    @Column(name = "complejidad_proyecto", length = 50)
    private String complejidadProyecto;
    
    /** Fecha de inicio del proyecto */
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio_proyecto")
    private LocalDate fechaInicioProyecto;
    
    /** Fecha de finalización del proyecto */
    @Column(name = "fecha_fin_proyecto")
    private LocalDate fechaFinProyecto;
    
    /** Estado actual del proyecto */
    @Column(name = "estado_proyecto", length = 50)
    private String estadoProyecto;
    
    /** Lista de requisitos del proyecto */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-requisitos")
    private List<Requisito> requisitos;
    
    /** Lista de sprints del proyecto */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-sprints")
    private List<Sprint> sprints;
    
    /** Lista de colaboradores asignados al proyecto */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-colaboradores")
    private List<Colaborador> colaboradores;
    
    /** Lista de presupuestos del proyecto */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-presupuestos")
    private List<Presupuesto> presupuestos;
}
