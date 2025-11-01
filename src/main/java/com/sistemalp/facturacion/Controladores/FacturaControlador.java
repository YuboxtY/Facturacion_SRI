package com.sistemalp.facturacion.Controladores;


import com.sistemalp.facturacion.Entidades.Factura;
import com.sistemalp.facturacion.Servicios.FacturaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaControlador {

    private  FacturaServicio facturaService;

    public FacturaControlador(FacturaServicio facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaService.listarFacturas();
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return facturaService.obtenerporID(id);
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        // Llama al método 'crearFactura' que creamos en el servicio,
        // el cual descuenta stock y calcula totales.
        return facturaService.crearFactura(factura);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable Long id, @RequestBody Factura facturaDatos) {
        return facturaService.actualizarFactura(id, facturaDatos);
    }
}