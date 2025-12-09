package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa un Cliente en el sistema.
 * Mapea la tabla "cliente" de la base de datos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Entity
@Table(name = "CLIENTES")
public class Cliente {
    
    /** Identificador único del cliente (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    /** Número de teléfono del cliente */
    @Column(name = "telefono_cliente")
    private String telefonoCliente;
    
    /** Correo electrónico del cliente */
    @Column(name = "email_cliente")
    private String emailCliente;
    
    /** Nombre de la empresa del cliente */
    @Column(name = "empresa_cliente")
    private String empresaCliente;
    
    /** RFC (Registro Federal de Contribuyentes) del cliente */
    @Column(name = "rfc_cliente")
    private String rfcCliente;

    /**
     * Obtiene el ID del cliente.
     * @return ID del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el ID del cliente.
     * @param idCliente ID del cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return Teléfono del cliente
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * Establece el teléfono del cliente.
     * @param telefonoCliente Teléfono del cliente
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * Obtiene el email del cliente.
     * @return Email del cliente
     */
    public String getEmailCliente() {
        return emailCliente;
    }

    /**
     * Establece el email del cliente.
     * @param emailCliente Email del cliente
     */
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    /**
     * Obtiene el nombre de la empresa del cliente.
     * @return Nombre de la empresa
     */
    public String getEmpresaCliente() {
        return empresaCliente;
    }

    /**
     * Establece el nombre de la empresa del cliente.
     * @param empresaCliente Nombre de la empresa
     */
    public void setEmpresaCliente(String empresaCliente) {
        this.empresaCliente = empresaCliente;
    }

    /**
     * Obtiene el RFC del cliente.
     * @return RFC del cliente
     */
    public String getRfcCliente() {
        return rfcCliente;
    }

    /**
     * Establece el RFC del cliente.
     * @param rfcCliente RFC del cliente
     */
    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    /**
     * Valida los datos del cliente antes de registrarlo.
     * @throws IllegalArgumentException si el email está vacío o es nulo
     */
    public void registrar() {
        if (this.emailCliente == null || this.emailCliente.isEmpty()) {
            throw new IllegalArgumentException("El email es requerido");
        }
    }

    /**
     * Valida que el cliente tenga un ID antes de actualizarlo.
     * @throws IllegalStateException si el ID del cliente es inválido
     */
    public void actualizar() {
        if (this.idCliente <= 0) {
            throw new IllegalStateException("No se puede actualizar un cliente sin ID");
        }
    }

}