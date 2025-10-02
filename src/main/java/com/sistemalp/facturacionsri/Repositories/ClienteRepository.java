package com.sistemalp.facturacionsri.Repositories;

import com.sistemalp.facturacionsri.Domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
