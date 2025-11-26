package com.sistemalp.facturacion.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType .IDENTITY)
    private Long Id;
    private String nombreUsuario;
    private String correo;
    private String password;
    private String username;
    private String estaActivo;

    @ManyToOne
    @JoinColumn (name = "tipoDeUsuarioId")
    private TipoDeUsuario tipoDeUsuario;

}
