package com.sistemalp.facturacion.Repositorios;

import com.sistemalp.facturacion.Entidades.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRespositorio extends JpaRepository<DetalleFactura, Long> {
}
