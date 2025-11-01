package com.sistemalp.facturacion.Servicios;

import com.sistemalp.facturacion.Entidades.DetalleFactura;
import com.sistemalp.facturacion.Entidades.Factura;
import com.sistemalp.facturacion.Entidades.Producto;
import com.sistemalp.facturacion.Repositorios.ClienteRepositorio;
import com.sistemalp.facturacion.Repositorios.FacturaRepositorio;
import com.sistemalp.facturacion.Repositorios.ProductoRepositorio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacturaServicio {

    private final FacturaRepositorio facturaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public FacturaServicio(FacturaRepositorio facturaRepositorio,
                           ProductoRepositorio productoRepositorio,
                           ClienteRepositorio clienteRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Transactional
    public Factura crearFactura(Factura factura) {


        if (factura.getCliente() == null || factura.getCliente().getClienteId() == null) {
            throw new IllegalArgumentException("La factura debe estar asociada a un cliente válido.");
        }
        clienteRepositorio.findById(factura.getCliente().getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + factura.getCliente().getClienteId()));


        if (factura.getDetalles() == null || factura.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La factura debe contener al menos un producto.");
        }


        double subtotal15 = 0.0;
        double subtotal0 = 0.0;
        double iva15 = 0.0;
        double descuento = (factura.getFacturaDescuento() != null) ? factura.getFacturaDescuento() : 0.0;


        factura.setFacturaFecha(LocalDateTime.now());
        factura.setFacturaEstado(1);


        for (DetalleFactura detalle : factura.getDetalles()) {
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad del producto debe ser mayor a cero.");
            }

            Producto producto = productoRepositorio.findById(detalle.getProducto().getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getProductoId()));

            if (producto.getProductoStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para: " + producto.getProductoNombre());
            }

            producto.setProductoStock(producto.getProductoStock() - detalle.getCantidad());
            productoRepositorio.save(producto);

            detalle.setPrecioUnitario(producto.getProductoPrecio());
            double subtotalDetalle = producto.getProductoPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotalDetalle);
            detalle.setFactura(factura);
            if (producto.isProductoGravaIva()) {
                subtotal15 += subtotalDetalle;
            } else {
                subtotal0 += subtotalDetalle;
            }
        }
        iva15 = subtotal15 * 0.15;
        double totalCalculado = (subtotal15 + subtotal0 + iva15) - descuento;
        factura.setFacturaSubtotal15(subtotal15);
        factura.setFacturaSubtotal0(subtotal0);
        factura.setFacturaIva15(iva15);
        factura.setFacturaTotal(totalCalculado);
        return facturaRepositorio.save(factura);
    }

    public List<Factura> listarFacturas() {
        return facturaRepositorio.findAll();
    }

    public Factura obtenerporID(Long id) {
        return facturaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    @Transactional
    public void eliminarFactura(Long id) {
        Factura factura = this.obtenerporID(id);
        for (DetalleFactura detalle : factura.getDetalles()) {
            Producto producto = detalle.getProducto();
            if (producto != null) {
                producto.setProductoStock(producto.getProductoStock() + detalle.getCantidad());
                productoRepositorio.save(producto);
            }
        }

        facturaRepositorio.delete(factura);
    }

    @Transactional
    public Factura actualizarFactura(Long id, Factura facturaActualizada) {
        Factura facturaExistente = this.obtenerporID(id);

        facturaExistente.setFacturaNumero(facturaActualizada.getFacturaNumero());
        facturaExistente.setFacturaEstado(facturaActualizada.getFacturaEstado());

        return facturaRepositorio.save(facturaExistente);
    }
}