package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Entidad que representa un cliente en el sistema.
 * Contiene la información básica del cliente y su relación con los proyectos.
 */
@Data
@Getter
@Entity
@Table(name = "CLIENTES")
public class Cliente {
    
    /** Identificador único del cliente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;
    
    /** Nombre de la empresa del cliente */
    @Column(name = "empresa_cliente", nullable = false, length = 150)
    private String empresaCliente;
    
    /** RFC del cliente */
    @Column(name = "rfc_cliente", nullable = false, length = 13)
    private String rfcCliente;
    
    /** Correo electrónico del cliente */
    @Column(name = "email_cliente", nullable = false, length = 100)
    private String emailCliente;
    
    /** Teléfono de contacto del cliente */
    @Column(name = "telefono_cliente", length = 15)
    private String telefonoCliente;
    
    /** Lista de proyectos asociados al cliente */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Proyecto> proyectos;

    public Cliente() {}
}
