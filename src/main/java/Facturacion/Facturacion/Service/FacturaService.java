package Facturacion.Facturacion.Service;



import Facturacion.Facturacion.Modelo.*;
import Facturacion.Facturacion.Repositorio.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final RepositorioProducto productoRepository;
    private final DetalleFacturaRepository detalleFacturaRepository;

    public FacturaService(FacturaRepository facturaRepository,
                          RepositorioProducto productoRepository,
                          DetalleFacturaRepository detalleFacturaRepository) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    @Transactional
    public Factura crearFactura(Factura factura) {
        if (factura.getCliente() == null || factura.getCliente().getId() == null) {
            throw new IllegalArgumentException("La factura debe estar asociada a un cliente válido.");
        }

        if (factura.getDetalles() == null || factura.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La factura debe contener al menos un producto.");
        }

        double total = 0.0;
        factura.setFecha(LocalDate.now());

        for (DetalleFactura detalle : factura.getDetalles()) {
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
            }

            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getId()));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Actualizar stock
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // Calcular subtotal
            double subtotal = producto.getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setFactura(factura);
            total += subtotal;
        }

        factura.setTotal(total);
        return facturaRepository.save(factura);
    }

    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    public Factura obtenerFactura(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    public void eliminarFactura(Long id) {
        if (!facturaRepository.existsById(id))
            throw new RuntimeException("Factura no encontrada con ID: " + id);

        facturaRepository.deleteById(id);
    }
}

