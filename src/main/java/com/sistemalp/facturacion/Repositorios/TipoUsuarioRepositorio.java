package com.sistemalp.facturacion.Repositorios;

import com.sistemalp.facturacion.Entidades.TipoDeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepositorio extends JpaRepository <TipoDeUsuario, Long > {
}
