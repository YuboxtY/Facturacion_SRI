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
        productoRepositorio.deleteById(id);
    }
}
