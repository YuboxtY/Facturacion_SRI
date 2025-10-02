package com.sistemalp.facturacionsri.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
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

}
