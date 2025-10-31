package Facturacion.Facturacion.Service;


import Facturacion.Facturacion.Modelo.DetalleFactura;
import Facturacion.Facturacion.Repositorio.DetalleFacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    private final DetalleFacturaRepository detalleFacturaRepository;

    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public DetalleFactura crearDetalle(DetalleFactura detalle) {
        if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        if (detalle.getProducto() == null || detalle.getProducto().getId() == null) {
            throw new IllegalArgumentException("El detalle debe tener un producto válido.");
        }

        if (detalle.getFactura() == null || detalle.getFactura().getId() == null) {
            throw new IllegalArgumentException("El detalle debe estar asociado a una factura válida.");
        }

        return detalleFacturaRepository.save(detalle);
    }

    public List<DetalleFactura> listarDetalles() {
        return detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> obtenerPorId(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public void eliminarDetalle(Long id) {
        if (!detalleFacturaRepository.existsById(id))
            throw new RuntimeException("Detalle no encontrado con ID: " + id);

        detalleFacturaRepository.deleteById(id);
    }
}
