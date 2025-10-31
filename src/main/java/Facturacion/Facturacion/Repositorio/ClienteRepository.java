package Facturacion.Facturacion.Repositorio;

import Facturacion.Facturacion.Modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente , Long> {
}
