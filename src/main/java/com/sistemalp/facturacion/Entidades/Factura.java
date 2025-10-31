package com.sistemalp.facturacion.Entidades;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long facturaId;
    private String facturaNumero;
    private LocalDateTime facturaFecha;
    private Double facturaTotal;
    private Integer facturaEstado;
    private Double facturaIva15;
    private Double facturaSubtotal0;
    private Double facturaSubtotal15;
    private Double facturaDescuento;
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    // 4. AÑADIR ESTE BLOQUE:
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles;
}
