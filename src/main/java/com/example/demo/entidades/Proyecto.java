package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "PROYECTOS")
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int idProyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente_fk", nullable = false)
    @JsonBackReference
    private Cliente cliente;
    
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    @Column(name = "nombre_proyecto", nullable = false, length = 200)
    private String nombreProyecto;
    
    @Column(name = "descripcion_proyecto", columnDefinition = "TEXT")
    private String descripcionProyecto;
    
    @Column(name = "complejidad_proyecto", length = 50)
    private String complejidadProyecto;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio_proyecto")
    private LocalDate fechaInicioProyecto;
    
    @Column(name = "fecha_fin_proyecto")
    private LocalDate fechaFinProyecto;
    
    @Column(name = "estado_proyecto", length = 50)
    private String estadoProyecto;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-requisitos")
    private List<Requisito> requisitos;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-sprints")
    private List<Sprint> sprints;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-colaboradores")
    private List<Colaborador> colaboradores;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    @JsonManagedReference("proyecto-presupuestos")
    private List<Presupuesto> presupuestos;
}
