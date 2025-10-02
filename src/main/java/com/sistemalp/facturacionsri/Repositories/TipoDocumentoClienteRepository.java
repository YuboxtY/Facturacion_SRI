package com.sistemalp.facturacionsri.Repositories;

import com.sistemalp.facturacionsri.Domain.TipoDocumentoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoClienteRepository extends JpaRepository <TipoDocumentoCliente, String> {
}
