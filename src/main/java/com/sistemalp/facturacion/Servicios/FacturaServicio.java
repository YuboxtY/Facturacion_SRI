package com.sistemalp.facturacion.Servicios;

import com.sistemalp.facturacion.Entidades.DetalleFactura;
import com.sistemalp.facturacion.Entidades.Factura;
import com.sistemalp.facturacion.Entidades.Producto;
import com.sistemalp.facturacion.Repositorios.ClienteRepositorio;
import com.sistemalp.facturacion.Repositorios.FacturaRepositorio;
import com.sistemalp.facturacion.Repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacturaServicio {
    private  FacturaRepositorio facturaRepositorio;
    private  ProductoRepositorio productoRepositorio;
    private  ClienteRepositorio clienteRepositorio;

    @Transactional
    public Factura crearFactura(Factura factura) {

        // 1. Validar Cliente
        if (factura.getCliente() == null || factura.getCliente().getClienteId() == null) {
            throw new IllegalArgumentException("La factura debe estar asociada a un cliente válido.");
        }
        // Verificamos que el cliente exista
        clienteRepositorio.findById(factura.getCliente().getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + factura.getCliente().getClienteId()));

        // 2. Validar Detalles
        if (factura.getDetalles() == null || factura.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La factura debe contener al menos un producto.");
        }

        double totalCalculado = 0.0;

        // 3. Asignar Fecha Actual
        factura.setFacturaFecha(LocalDateTime.now());

        // 4. Procesar Detalles y Stock
        for (DetalleFactura detalle : factura.getDetalles()) {
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad del producto debe ser mayor a cero.");
            }

            // Buscar el producto en la BD
            Producto producto = productoRepositorio.findById(detalle.getProducto().getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getProductoId()));

            // Validar Stock
            if (producto.getProductoStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getProductoNombre());
            }

            // Actualizar Stock
            producto.setProductoStock(producto.getProductoStock() - detalle.getCantidad());
            productoRepositorio.save(producto);

            // Calcular subtotal del detalle
            double subtotal = producto.getProductoPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);

            // Importante: Asignar la factura (padre) al detalle (hijo)
            detalle.setFactura(factura);

            // Sumar al total general
            totalCalculado += subtotal;
        }

        // 5. Asignar Total y Guardar
        // (Aquí deberías también calcular IVA y descuentos si los necesitas)
        factura.setFacturaTotal(totalCalculado);

        // Gracias a CascadeType.ALL en la entidad Factura,
        // al guardar la factura, se guardarán automáticamente los detalles asociados.
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
        if (!facturaRepositorio.existsById(id)) {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
        // (Nota: Si quieres reponer el stock al eliminar una factura,
        // necesitarías lógica adicional aquí antes de borrar)
        facturaRepositorio.deleteById(id);
    }

    @Transactional
    public Factura actualizarFactura(Long id, Factura facturaActualizada) {
        Factura facturaExistente = facturaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));

        facturaExistente.setFacturaNumero(facturaActualizada.getFacturaNumero());
        facturaExistente.setFacturaEstado(facturaActualizada.getFacturaEstado());


        return facturaRepositorio.save(facturaExistente);
    }

}
