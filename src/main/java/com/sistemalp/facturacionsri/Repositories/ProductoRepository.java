package com.sistemalp.facturacionsri.Repositories;

import com.sistemalp.facturacionsri.Domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository  <Producto, Long> {
}
