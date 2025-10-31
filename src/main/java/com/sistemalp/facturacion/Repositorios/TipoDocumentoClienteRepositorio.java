package com.sistemalp.facturacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalp.facturacion.Entidades.TipoDocumentoCliente;
@Repository
public interface TipoDocumentoClienteRepositorio extends JpaRepository<TipoDocumentoCliente,String>{
    
}
