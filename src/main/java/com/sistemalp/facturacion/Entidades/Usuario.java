package com.sistemalp.facturacion.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private String username;
    private String estaActivo;

    @ManyToOne
    @JoinColumn(name = "tipodeusuario_id", nullable = false)
    private TipodeUsuario tipodeusuario;

}
