package Facturacion.Facturacion.Controlador;


import Facturacion.Facturacion.Modelo.DetalleFactura;
import Facturacion.Facturacion.Service.DetalleFacturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class ControladorDetalleFactura {

    private final DetalleFacturaService detalleFacturaService;

    public ControladorDetalleFactura(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    public List<DetalleFactura> listarDetalles() {
        return detalleFacturaService.listarDetalles();
    }

    @GetMapping("/{id}")
    public DetalleFactura obtenerDetalle(@PathVariable Long id) {
        return detalleFacturaService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));
    }

    @PostMapping
    public DetalleFactura crearDetalle(@RequestBody DetalleFactura detalle) {
        return detalleFacturaService.crearDetalle(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id) {
        detalleFacturaService.eliminarDetalle(id);
    }
}
