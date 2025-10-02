package com.sistemalp.facturacionsri.Repositories;

import com.sistemalp.facturacionsri.Domain.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository  extends JpaRepository <TipoDocumento, Long> {
}
