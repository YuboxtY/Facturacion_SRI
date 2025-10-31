package com.sistemalp.facturacion.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemalp.facturacion.Entidades.Producto;
import com.sistemalp.facturacion.Repositorios.ProductoRepositorio;
@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Producto guardar(Producto producto){
        return productoRepositorio.save(producto);
    }

    public List<Producto> listarAll(){
        return productoRepositorio.findAll();
    }

    public Producto buscarId(Long id){
        return productoRepositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        if (!productoRepositorio.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepositorio.deleteById(id);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        // 1. Buscamos el producto existente
        Producto productoExistente = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // 2. Actualizamos todos los campos con los datos nuevos
        productoExistente.setProductoNombre(productoActualizado.getProductoNombre());
        productoExistente.setProductoSerial(productoActualizado.getProductoSerial());
        productoExistente.setProductoPrecio(productoActualizado.getProductoPrecio());
        productoExistente.setProductoStock(productoActualizado.getProductoStock());
        productoExistente.setProductoTasa(productoActualizado.getProductoTasa());
        productoExistente.setProductoEstado(productoActualizado.getProductoEstado());
        productoExistente.setProductoCategoria(productoActualizado.getProductoCategoria());

        // 3. Guardamos los cambios
        return productoRepositorio.save(productoExistente);
    }
}