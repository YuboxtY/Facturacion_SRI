package com.sistemalp.facturacionsri.Repositories;

import com.sistemalp.facturacionsri.Domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository <Factura, Long> {
}
