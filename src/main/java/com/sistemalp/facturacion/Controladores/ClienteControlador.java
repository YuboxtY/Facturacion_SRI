package com.sistemalp.facturacion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sistemalp.facturacion.Dto.ClienteConDocumentoDto;
import com.sistemalp.facturacion.Entidades.Cliente;
import com.sistemalp.facturacion.Servicios.ClienteServicio;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/crear")
    public Cliente guardar(@RequestBody ClienteConDocumentoDto cliente){
        return clienteServicio.guardar(cliente);
    }
    @GetMapping("/listar")
    public List<Cliente> listasAll(){
        return clienteServicio.listarAll();
    }
    @GetMapping("/buscarporid/{id}")
    public Cliente buscarId(@PathVariable Long id){
        return clienteServicio.buscarId(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public void elimnar(@PathVariable Long id){
        clienteServicio.eliminar(id);
    }

    @PutMapping("actualizar/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDatos) {
        // Este endpoint espera un objeto Cliente, no el DTO
        return clienteServicio.actualizar(id, clienteDatos);
    }

}
