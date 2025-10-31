package Facturacion.Facturacion.Controlador;


import Facturacion.Facturacion.Modelo.Factura;
import Facturacion.Facturacion.Service.FacturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class ControladorFactura {

    private final FacturaService facturaService;

    public ControladorFactura(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaService.listarFacturas();
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return facturaService.obtenerFactura(id);
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        return facturaService.crearFactura(factura);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
    }
}
