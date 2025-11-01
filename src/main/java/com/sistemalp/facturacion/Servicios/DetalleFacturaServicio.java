
package com.sistemalp.facturacion.Servicios;

import com.sistemalp.facturacion.Entidades.DetalleFactura;
import com.sistemalp.facturacion.Repositorios.DetalleFacturaRespositorio;
import com.sistemalp.facturacion.Repositorios.FacturaRepositorio;
import com.sistemalp.facturacion.Repositorios.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaServicio {

    private DetalleFacturaRespositorio detalleFacturaRepository;
    private ProductoRepositorio productoRepositorio;
    private FacturaRepositorio facturaRepositorio;

    public DetalleFacturaServicio(DetalleFacturaRespositorio detalleFacturaRepository,
                                 ProductoRepositorio productoRepositorio,
                                 FacturaRepositorio facturaRepositorio) {
        this.detalleFacturaRepository = detalleFacturaRepository;
        this.productoRepositorio = productoRepositorio;
        this.facturaRepositorio = facturaRepositorio;
    }

    public DetalleFactura crearDetalle(DetalleFactura detalle) {
        if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        if (detalle.getProducto() == null || detalle.getProducto().getProductoId() == null) {
            throw new IllegalArgumentException("El detalle debe tener un producto válido.");
        }

        productoRepositorio.findById(detalle.getProducto().getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getProductoId()));

        if (detalle.getFactura() == null || detalle.getFactura().getFacturaId() == null) {
            throw new IllegalArgumentException("El detalle debe estar asociado a una factura válida.");
        }

        facturaRepositorio.findById(detalle.getFactura().getFacturaId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + detalle.getFactura().getFacturaId()));
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