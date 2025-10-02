package com.sistemalp.facturacionsri.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class TipoDocumentoCliente {
    @Id
    private String tipoDocumentoCliente;
    private LocalDateTime tipoDocumentoFecha;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipoDucumentoId", nullable = false)
    private TipoDocumento tipoDocumento;
}
