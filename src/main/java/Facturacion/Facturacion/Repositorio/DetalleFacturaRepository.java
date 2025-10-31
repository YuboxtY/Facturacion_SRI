package Facturacion.Facturacion.Repositorio;

import Facturacion.Facturacion.Modelo.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
}
