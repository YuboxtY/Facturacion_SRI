package com.sistemalp.facturacion.Repositorios;

import com.sistemalp.facturacion.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> {

    Optional<Usuario> findByUsername(String username); // Método para buscar un usuario por su nombre de usuario
    boolean existsByUsername(String username); // Método para verificar si un nombre de usuario ya existe
}
