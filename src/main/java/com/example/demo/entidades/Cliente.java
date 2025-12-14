package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@Entity
@Table(name = "CLIENTES")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;
    
    @Column(name = "empresa_cliente", nullable = false, length = 150)
    private String empresaCliente;
    
    @Column(name = "rfc_cliente", nullable = false, length = 13)
    private String rfcCliente;
    
    @Column(name = "email_cliente", nullable = false, length = 100)
    private String emailCliente;
    
    @Column(name = "telefono_cliente", length = 15)
    private String telefonoCliente;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Proyecto> proyectos;

    public Cliente() {}
}
