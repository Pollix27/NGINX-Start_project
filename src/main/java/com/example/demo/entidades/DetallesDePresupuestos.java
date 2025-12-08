package com.example.demo.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa los Detalles de un Presupuesto.
 * Mapea la tabla "detalles_de_presupuestos" de la base de datos.
 * Contiene información financiera y de pago del presupuesto.
 * 
 * @author NGINX
 * @version 1.0
 */
@Entity
@Table(name = "detalles_de_presupuestos")
public class DetallesDePresupuestos {
    
    /** Identificador único del detalle de presupuesto (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_presupuesto")
    private int idDetallePresupuesto;
    
    /** Presupuesto al que pertenece este detalle (relación Many-to-One) */
    @ManyToOne
    @JoinColumn(name = "id_presupuesto_fk")
    private PresupuestosDeProyecto presupuesto;
    
    /** Método de pago utilizado (efectivo, tarjeta, transferencia, etc.) */
    @Column(name = "metodo_de_pago")
    private String metodoDePago;
    
    /** Monto adicional agregado al presupuesto */
    @Column(name = "monto_agregado")
    private BigDecimal montoAgregado;
    
    /** Total de servicios adicionales */
    @Column(name = "total_adicional")
    private BigDecimal totalAdicional;
    
    /** Subtotal del presupuesto (antes de impuestos) */
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    
    /** IVA aplicado al presupuesto */
    @Column(name = "iva")
    private BigDecimal iva;

    public int getIdDetallePresupuesto() {
        return idDetallePresupuesto;
    }

    public void setIdDetallePresupuesto(int idDetallePresupuesto) {
        this.idDetallePresupuesto = idDetallePresupuesto;
    }

    public PresupuestosDeProyecto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(PresupuestosDeProyecto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public BigDecimal getMontoAgregado() {
        return montoAgregado;
    }

    public void setMontoAgregado(BigDecimal montoAgregado) {
        this.montoAgregado = montoAgregado;
    }

    public BigDecimal getTotalAdicional() {
        return totalAdicional;
    }

    public void setTotalAdicional(BigDecimal totalAdicional) {
        this.totalAdicional = totalAdicional;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * Calcula el subtotal sumando el monto agregado y el total adicional.
     * El resultado se almacena en el atributo subtotal.
     */
    public void calcularSubtotal() {
        this.subtotal = this.montoAgregado.add(this.totalAdicional);
    }
}
