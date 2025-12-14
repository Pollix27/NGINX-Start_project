package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PRESUPUESTOS")
public class Presupuesto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private int idPresupuesto;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-presupuestos")
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_requisitos_fk")
    @JsonBackReference("requisito-presupuestos")
    private Requisito requisito;
    
    @ManyToOne
    @JoinColumn(name = "id_colaborador_fk")
    @JsonBackReference("colaborador-presupuestos")
    private Colaborador colaborador;
    
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    @Column(name = "iva", precision = 10, scale = 2)
    private BigDecimal iva;
    
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;
    
    @Column(name = "fecha_elaboracion_presupuesto")
    private LocalDate fechaElaboracionPresupuesto;
    
    @Column(name = "estado_presupuesto", length = 50)
    private String estadoPresupuesto;
    
    @Column(name = "metodo_pago_presupuesto", length = 50)
    private String metodoPagoPresupuesto;
    
    @Column(name = "servicios_adicionales_presupuesto", columnDefinition = "TEXT")
    private String serviciosAdicionalesPresupuesto;
    
    @Column(name = "descripcion_presupuesto", columnDefinition = "TEXT")
    private String descripcionPresupuesto;

    public Presupuesto() {}

}

