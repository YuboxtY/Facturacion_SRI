package com.sistemalp.facturacion.Controladores;

import com.sistemalp.facturacion.Entidades.Producto;
import com.sistemalp.facturacion.Servicios.ProductoServicio; // Usamos el servicio correcto
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    // 3. Inyectamos el servicio correcto: ProductoServicio
    private ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        // 4. Usamos el método correcto: listarAll()
        return productoServicio.listarAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        // 5. Usamos el método correcto: buscarId()
        Producto producto = productoServicio.buscarId(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        return producto;
    }

    @PostMapping("/Crear")
    public Producto crearProducto(@RequestBody Producto producto) {
        // 6. Usamos el método correcto: guardar()
        return productoServicio.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto datos) {
        // 7. (Ver Paso 2) Usaremos un método 'actualizar' que debemos añadir al servicio
        return productoServicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        // 8. Usamos el método correcto: eliminar()
        productoServicio.eliminar(id);
    }
}