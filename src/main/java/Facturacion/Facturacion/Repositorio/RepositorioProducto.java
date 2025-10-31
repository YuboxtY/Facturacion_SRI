package Facturacion.Facturacion.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Facturacion.Facturacion.Modelo.Producto;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
}
