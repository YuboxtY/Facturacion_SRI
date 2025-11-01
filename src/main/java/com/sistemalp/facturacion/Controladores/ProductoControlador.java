package com.sistemalp.facturacion.Controladores;

import com.sistemalp.facturacion.Entidades.Producto;
import com.sistemalp.facturacion.Servicios.ProductoServicio; // Usamos el servicio correcto
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {


    private ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoServicio.listarAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        Producto producto = productoServicio.buscarId(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        return producto;
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServicio.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto datos) {
        return productoServicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoServicio.eliminar(id);
    }
}