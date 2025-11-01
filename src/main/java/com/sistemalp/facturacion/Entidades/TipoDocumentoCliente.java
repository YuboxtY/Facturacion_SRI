package com.sistemalp.facturacion.Entidades;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TipoDocumentoCliente {
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    @JsonBackReference
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "tipoDocumentoId", nullable = false)
    private TipoDocumento tipoDocumento;
    @Id
    private String numeroDocumentoCliente;
    private LocalDateTime tipoDocumentoFecha;
}
