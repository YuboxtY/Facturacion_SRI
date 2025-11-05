package com.sistemalp.facturacion.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TipodeUsuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;
    private String rol;
    private String Descripcion;

}
