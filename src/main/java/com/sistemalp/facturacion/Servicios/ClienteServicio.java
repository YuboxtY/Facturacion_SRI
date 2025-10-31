package com.sistemalp.facturacion.Servicios;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Importa la anotación Transactional
import org.springframework.transaction.annotation.Transactional; // <-- 1. IMPORT AÑADIDO

import com.sistemalp.facturacion.Dto.ClienteConDocumentoDto;
import com.sistemalp.facturacion.Dto.DocumentoListaClienteDto;
import com.sistemalp.facturacion.Entidades.Cliente;
import com.sistemalp.facturacion.Entidades.TipoDocumento;
import com.sistemalp.facturacion.Entidades.TipoDocumentoCliente;
import com.sistemalp.facturacion.Repositorios.ClienteRepositorio;
import com.sistemalp.facturacion.Repositorios.TipoDocumentoClienteRepositorio;
import com.sistemalp.facturacion.Repositorios.TipoDocumentoRepositorio;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private TipoDocumentoRepositorio tipoDocumentoRepositorio;
    @Autowired
    private TipoDocumentoClienteRepositorio tipoDocumentoClienteRepositorio;

    // <-- 2. ANOTACIÓN AÑADIDA PARA GARANTIZAR QUE TODO SE CUMPLA O NADA SE CUMPLA
    @Transactional
    public Cliente guardar(ClienteConDocumentoDto clienteDto){
        List<DocumentoListaClienteDto> documentos=clienteDto.getDocumentos();

        if( documentos == null || documentos.isEmpty() ){
            throw new RuntimeException("Error no tiene documentos");
        }

        Cliente cliente=clienteDto.getCliente();
        clienteRepositorio.save(cliente); // Esto ahora es parte de la transacción

        for (DocumentoListaClienteDto doc : documentos) {
            TipoDocumentoCliente tipoDocumentoCliente=new TipoDocumentoCliente();
            tipoDocumentoCliente.setCliente(cliente);
            System.err.println(doc.getNumeroDocumentoCliente());
            System.err.println(doc.getTipoDocumentoId());
            tipoDocumentoCliente.setNumeroDocumentoCliente(doc.getNumeroDocumentoCliente());

            // <-- 3. LÍNEA MODIFICADA (MEJORA OPCIONAL)
            // Se cambió getById por findById para lanzar un error claro si el ID no existe
            TipoDocumento tipoDocumento = tipoDocumentoRepositorio.findById(doc.getTipoDocumentoId())
                    .orElseThrow(() -> new RuntimeException("TipoDocumento no encontrado con ID: " + doc.getTipoDocumentoId()));

            tipoDocumentoCliente.setTipoDocumento(tipoDocumento);
            tipoDocumentoCliente.setTipoDocumentoFecha(LocalDateTime.now());

            tipoDocumentoClienteRepositorio.save(tipoDocumentoCliente); // Esto también es parte de la transacción
        }
        return cliente; // La transacción se confirma (commit) aquí si todo salió bien
    }

    public List<Cliente> listarAll(){
        return clienteRepositorio.findAll();
    }

    public Cliente buscarId(Long id){
        return clienteRepositorio.findById(id).orElse(null);
    }

    // Nota: También es buena práctica hacer transaccional el método de eliminar.
    @Transactional
    public void eliminar(Long id){
        // Puedes agregar una verificación si lo deseas, aunque deleteById ya es transaccional por sí mismo.
        if (!clienteRepositorio.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado para eliminar, ID: " + id);
        }
        // Deberías considerar qué pasa con los TipoDocumentoCliente asociados (borrado en cascada o manual)
        clienteRepositorio.deleteById(id);
    }
}