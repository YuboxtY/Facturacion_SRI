package com.sistemalp.facturacion.Repositorios;

import com.sistemalp.facturacion.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long >{

}
