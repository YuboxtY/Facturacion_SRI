package com.sistemalp.facturacion.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter; // Es mejor práctica usar @Getter y @Setter que @Data
import lombok.Setter; // @Data puede causar problemas con JPA (equals/hashcode)

@Entity
@Getter // Cambiado de @Data
@Setter // Cambiado de @Data
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    @JsonBackReference // <-- ¡AÑADE ESTA LÍNEA!
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;


    @Column(nullable = false)
    private Double precioUnitario;

    private Double subtotal;
}