
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

        // 3. VALIDACIÓN CORREGIDA (usando getters de Lombok correctos)

        // Validar Producto
        if (detalle.getProducto() == null || detalle.getProducto().getProductoId() == null) {
            throw new IllegalArgumentException("El detalle debe tener un producto válido.");
        }
        // Validar que el producto exista en la BD
        productoRepositorio.findById(detalle.getProducto().getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getProductoId()));

        // Validar Factura
        if (detalle.getFactura() == null || detalle.getFactura().getFacturaId() == null) {
            throw new IllegalArgumentException("El detalle debe estar asociado a una factura válida.");
        }
        // Validar que la factura exista en la BD
        facturaRepositorio.findById(detalle.getFactura().getFacturaId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + detalle.getFactura().getFacturaId()));

        // (Nota: Este servicio 'crearDetalle' NO descuenta stock. 
        // El 'FacturaService' que hicimos antes es el que se encarga de eso 
        // cuando se crea la factura completa)

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