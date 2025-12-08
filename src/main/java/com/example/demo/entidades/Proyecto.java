package com.example.demo.entidades;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Entidad que representa un Proyecto en el sistema.
 * Mapea la tabla "proyecto" de la base de datos.
 * Cada proyecto está asociado a un cliente.
 * 
 * @author NGINX
 * @version 1.0
 */
@Entity
@Table(name = "proyecto")
public class Proyecto {

    /** Identificador único del proyecto (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int idProyecto;

    /** Nombre del proyecto */
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    
    /** Descripción detallada del proyecto */
    @Column(name = "descripcion_proyecto")
    private String descripcionProyecto;
    
    /** Nivel de complejidad del proyecto (Baja, Media, Alta) */
    @Column(name = "complejidad_proyecto")
    private String complejidadProyecto;
    
    /** Fecha de inicio del proyecto */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_proyecto")
    private Date fechaInicioProyecto;
    
    /** Fecha de finalización del proyecto */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin_proyecto")
    private Date fechaFinProyecto;
    
    /** Estado actual del proyecto (Planificación, En Progreso, En Pausa, Completado) */
    @Column(name = "estado_proyecto")
    private String estadoProyecto;
    
    /** Cliente al que pertenece el proyecto (relación Many-to-One) */
    @ManyToOne
    @JoinColumn(name = "id_cliente_fk")
    private Cliente cliente;

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getComplejidadProyecto() {
        return complejidadProyecto;
    }

    public void setComplejidadProyecto(String complejidadProyecto) {
        this.complejidadProyecto = complejidadProyecto;
    }

    public Date getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(Date fechaInicioProyecto) {
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public Date getFechaFinProyecto() {
        return fechaFinProyecto;
    }

    public void setFechaFinProyecto(Date fechaFinProyecto) {
        this.fechaFinProyecto = fechaFinProyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Valida los datos del proyecto antes de crearlo.
     * @throws IllegalArgumentException si el nombre del proyecto está vacío o es nulo
     */
    public void crear() {
        if (this.nombreProyecto == null || this.nombreProyecto.isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
    }

    /**
     * Valida que el proyecto tenga un ID antes de actualizar su estado.
     * @throws IllegalStateException si el ID del proyecto es inválido
     */
    public void actualizarEstado() {
        if (this.idProyecto <= 0) {
            throw new IllegalStateException("No se puede actualizar un proyecto sin ID");
        }
    }

    /**
     * Calcula la duración del proyecto en días.
     * @return Número de días entre la fecha de inicio y fin, o 0 si alguna fecha es nula
     */
    public long calcularDuracion() {
        if (fechaInicioProyecto != null && fechaFinProyecto != null) {
            return (fechaFinProyecto.getTime() - fechaInicioProyecto.getTime()) / (1000 * 60 * 60 * 24);
        }
        return 0;
    }
}
