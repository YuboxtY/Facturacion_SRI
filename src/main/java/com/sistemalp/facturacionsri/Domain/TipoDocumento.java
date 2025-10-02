package com.sistemalp.facturacionsri.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoDucumentoId;
    private String tipoDocumentoNombre;
    private String tipoDocumentoCodigo;
}
