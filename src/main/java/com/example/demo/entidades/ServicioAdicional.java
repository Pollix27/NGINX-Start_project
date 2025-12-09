package com.example.demo.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un Servicio Adicional.
 * Mapea la tabla "servicios_adicionales" de la base de datos.
 * Los servicios adicionales se asocian a los detalles de presupuestos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Entity
@Table(name = "SERVICIOS_ADICIONALES")
public class ServicioAdicional {
    
    /** Identificador único del servicio adicional (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_adicional")
    private int idServicioAdicional;
    
    /** Detalle de presupuesto al que pertenece este servicio (relación Many-to-One) */
    @ManyToOne
    @JoinColumn(name = "id_detalle_presupuesto_fk")
    private DetallePresupuesto detallePresupuesto;
    
    /** Descripción del servicio adicional */
    @Column(name = "descripcion_servicio_adicional")
    private String descripcionServicioAdicional;
    
    /** Monto o costo del servicio adicional */
    @Column(name = "monto_servicio_adicional")
    private BigDecimal montoServicioAdicional;

    public int getIdServicioAdicional() {
        return idServicioAdicional;
    }

    public void setIdServicioAdicional(int idServicioAdicional) {
        this.idServicioAdicional = idServicioAdicional;
    }

    public DetallePresupuesto getDetallePresupuesto() {
        return detallePresupuesto;
    }

    public void setDetallePresupuesto(DetallePresupuesto detallePresupuesto) {
        this.detallePresupuesto = detallePresupuesto;
    }

    public String getDescripcionServicioAdicional() {
        return descripcionServicioAdicional;
    }

    public void setDescripcionServicioAdicional(String descripcionServicioAdicional) {
        this.descripcionServicioAdicional = descripcionServicioAdicional;
    }

    public BigDecimal getMontoServicioAdicional() {
        return montoServicioAdicional;
    }

    public void setMontoServicioAdicional(BigDecimal montoServicioAdicional) {
        this.montoServicioAdicional = montoServicioAdicional;
    }

    /**
     * Retorna el monto del servicio adicional.
     * @return Monto del servicio adicional
     */
    public BigDecimal calcularAdicionales() {
        return this.montoServicioAdicional;
    }
}
