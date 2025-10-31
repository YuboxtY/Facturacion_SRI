package Facturacion.Facturacion.Repositorio;

import Facturacion.Facturacion.Modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
