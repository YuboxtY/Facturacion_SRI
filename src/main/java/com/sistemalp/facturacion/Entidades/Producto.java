package com.sistemalp.facturacion.Entidades;

import jakarta.persistence.*; // Importa @Column
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;

    private String productoSerial;
    private String productoNombre;
    private Double productoPrecio;
    private Double productoStock;
    private Double productoTasa; // (No usamos este, pero ahí está)
    private Integer productoEstado;
    private String productoCategoria;

    // --- ¡CAMPO AÑADIDO! ---
    // Este es el interruptor para el IVA.
    // 'columnDefinition' asegura que si no se envía, la BD asume 'true'.
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean productoGravaIva = true;
}