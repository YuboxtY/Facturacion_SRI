package Facturacion.Facturacion.Service;




import Facturacion.Facturacion.Modelo.Producto;
import Facturacion.Facturacion.Repositorio.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final RepositorioProducto productoRepository;

    public ProductoService(RepositorioProducto productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }

        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser nulo o negativo.");
        }

        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser nulo o negativo.");
        }

        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto actualizarProducto(Long id, Producto datos) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (datos.getNombre() != null && !datos.getNombre().isBlank()) {
            producto.setNombre(datos.getNombre());
        }

        if (datos.getDescripcion() != null) {
            producto.setDescripcion(datos.getDescripcion());
        }

        if (datos.getPrecio() != null && datos.getPrecio() >= 0) {
            producto.setPrecio(datos.getPrecio());
        }

        if (datos.getStock() != null && datos.getStock() >= 0) {
            producto.setStock(datos.getStock());
        }

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id))
            throw new RuntimeException("Producto no encontrado con ID: " + id);

        productoRepository.deleteById(id);
    }

    public void actualizarStock(Long id, int cantidadVendida) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        int nuevoStock = producto.getStock() - cantidadVendida;
        if (nuevoStock < 0)
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());

        producto.setStock(nuevoStock);
        productoRepository.save(producto);
    }
}
