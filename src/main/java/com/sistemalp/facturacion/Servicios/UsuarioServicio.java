package com.sistemalp.facturacion.Servicios;

import com.sistemalp.facturacion.Entidades.Usuario;
import com.sistemalp.facturacion.Repositorios.UsuarioRepositorio;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;// para que el password se guarde encriptado

    private Usuario guardar(Usuario usuario){
        //encriptar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepositorio.save(usuario);
    }

    private List<Usuario> listar(){
        return usuarioRepositorio.findAll();
    }

    private Usuario listarUsuario(Long id){
        return usuarioRepositorio.findById(id).orElse(null);
    }

    private Usuario findByUsername(String username){
        return usuarioRepositorio.findByUsername(username).orElse(null);
    }

    private void eliminar(Long id){
        usuarioRepositorio.deleteById(id);
    }

}
