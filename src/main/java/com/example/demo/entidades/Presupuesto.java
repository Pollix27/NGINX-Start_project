package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa un presupuesto en el sistema.
 * Contiene información financiera relacionada con un proyecto, requisito y colaborador.
 */
@Data
@Entity
@Table(name = "PRESUPUESTOS")
public class Presupuesto {
    
    /** Identificador único del presupuesto */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private int idPresupuesto;
    
    /** Proyecto al que pertenece el presupuesto */
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-presupuestos")
    private Proyecto proyecto;
    
    /** Requisito asociado al presupuesto (opcional) */
    @ManyToOne
    @JoinColumn(name = "id_requisitos_fk")
    @JsonBackReference("requisito-presupuestos")
    private Requisito requisito;
    
    /** Colaborador asociado al presupuesto (opcional) */
    @ManyToOne
    @JoinColumn(name = "id_colaborador_fk")
    @JsonBackReference("colaborador-presupuestos")
    private Colaborador colaborador;
    
    /** Subtotal del presupuesto antes de impuestos */
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    /** Monto del IVA aplicado */
    @Column(name = "iva", precision = 10, scale = 2)
    private BigDecimal iva;
    
    /** Total del presupuesto incluyendo impuestos */
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;
    
    /** Fecha de elaboración del presupuesto */
    @Column(name = "fecha_elaboracion_presupuesto")
    private LocalDate fechaElaboracionPresupuesto;
    
    /** Estado actual del presupuesto */
    @Column(name = "estado_presupuesto", length = 50)
    private String estadoPresupuesto;
    
    /** Método de pago del presupuesto */
    @Column(name = "metodo_pago_presupuesto", length = 50)
    private String metodoPagoPresupuesto;
    
    /** Servicios adicionales incluidos en el presupuesto */
    @Column(name = "servicios_adicionales_presupuesto", columnDefinition = "TEXT")
    private String serviciosAdicionalesPresupuesto;
    
    /** Descripción detallada del presupuesto */
    @Column(name = "descripcion_presupuesto", columnDefinition = "TEXT")
    private String descripcionPresupuesto;

    public Presupuesto() {}

}

