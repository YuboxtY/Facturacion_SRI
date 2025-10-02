package com.sistemalp.facturacionsri.Services;

import com.sistemalp.facturacionsri.Domain.Producto;
import com.sistemalp.facturacionsri.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepositorio;

    //Guardar un producto
    public Producto guardar (Producto producto){
        return productoRepositorio.save(producto);

    }

    //Listar todos los productos
    public void listarAll(){
        productoRepositorio.findAll();
    }

    //Buscar un producto por ID
    public void buscarProducto (Long id){
        productoRepositorio.findById(id).orElse(null);
    }

    //Eliminar un producto
    public void eliminar (Long id){
        productoRepositorio.deleteById(id);
    }



}
