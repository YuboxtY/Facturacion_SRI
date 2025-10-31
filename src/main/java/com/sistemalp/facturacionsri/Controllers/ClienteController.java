package com.sistemalp.facturacionsri.Controllers;

import com.sistemalp.facturacionsri.Domain.Cliente;
import com.sistemalp.facturacionsri.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteServicio;

    // El post es para guardar
    @PostMapping ("/guardar")
    // El request body convierte el json en objeto
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteServicio.guardar(cliente);
    }

    // El put es para actualizar
    @PutMapping("{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {

        Cliente clienteExistente = clienteServicio.buscarId(id);
        if (clienteExistente != null) {

            clienteExistente.setClienteNombre(cliente.getClienteNombre());
            clienteExistente.setClienteApellido(cliente.getClienteApellido());
            clienteExistente.setClienteDireccion(cliente.getClienteDireccion());
            clienteExistente.setClienteTelefono(cliente.getClienteTelefono());
            clienteExistente.setClienteMail(cliente.getClienteMail());

            return clienteServicio.guardar(clienteExistente);
        }
        return null;
    }

    @GetMapping
    public List<Cliente> listarAll() {
        return clienteServicio.listarAll();
    }

    @GetMapping ("{id}")
    public Cliente buscarCliente(Long id) {
        return clienteServicio.buscarId(id);
    }

    @DeleteMapping
    public void eliminar(@PathVariable Long id) {
        clienteServicio.eliminar(id);
    }
}
