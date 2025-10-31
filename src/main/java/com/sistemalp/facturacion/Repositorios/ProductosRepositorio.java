package com.sistemalp.facturacion.Repositorios;

import com.sistemalp.facturacion.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepositorio extends JpaRepository<Producto,Long> {
}
