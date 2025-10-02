package com.sistemalp.facturacionsri.Services;

import com.sistemalp.facturacionsri.Domain.Cliente;
import com.sistemalp.facturacionsri.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    //Inyectar el repositorio de clientesRepositorio CRUD
    @Autowired
    private ClienteRepository clienteRepositorio;

    //Guardar un cliente
    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    //Listar todos los clientes
    public List<Cliente> listarAll() {
        return clienteRepositorio.findAll();
    }

    //Buscar un cliente por ID
    public Cliente buscarId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    //Eliminar un cliente
    public void eliminar(Long id) {
        clienteRepositorio.deleteById(id);
    }


}
