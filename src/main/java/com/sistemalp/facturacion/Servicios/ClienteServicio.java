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

    @Transactional
    public Cliente guardar(ClienteConDocumentoDto clienteDto){
        List<DocumentoListaClienteDto> documentos=clienteDto.getDocumentos();

        if( documentos == null || documentos.isEmpty() ){
            throw new RuntimeException("Error no tiene documentos");
        }

        Cliente cliente=clienteDto.getCliente();
        clienteRepositorio.save(cliente);

        for (DocumentoListaClienteDto doc : documentos) {
            TipoDocumentoCliente tipoDocumentoCliente=new TipoDocumentoCliente();
            tipoDocumentoCliente.setCliente(cliente);
            System.err.println(doc.getNumeroDocumentoCliente());
            System.err.println(doc.getTipoDocumentoId());
            tipoDocumentoCliente.setNumeroDocumentoCliente(doc.getNumeroDocumentoCliente());


            TipoDocumento tipoDocumento = tipoDocumentoRepositorio.findById(doc.getTipoDocumentoId())
                    .orElseThrow(() -> new RuntimeException("TipoDocumento no encontrado con ID: " + doc.getTipoDocumentoId()));

            tipoDocumentoCliente.setTipoDocumento(tipoDocumento);
            tipoDocumentoCliente.setTipoDocumentoFecha(LocalDateTime.now());

            tipoDocumentoClienteRepositorio.save(tipoDocumentoCliente);
        }
        return cliente;
    }

    public List<Cliente> listarAll(){
        return clienteRepositorio.findAll();
    }

    public Cliente buscarId(Long id){
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void eliminar(Long id){
        if (!clienteRepositorio.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado para eliminar, ID: " + id);
        }
        clienteRepositorio.deleteById(id);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente clienteActualizado) {

        Cliente clienteExistente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));


        clienteExistente.setClienteNombre(clienteActualizado.getClienteNombre());
        clienteExistente.setClienteAplellido(clienteActualizado.getClienteAplellido());
        clienteExistente.setClienteDirecion(clienteActualizado.getClienteDirecion());
        clienteExistente.setClienteTelefono(clienteActualizado.getClienteTelefono());
        clienteExistente.setClienteMail(clienteActualizado.getClienteMail());
        clienteExistente.setClienteEstado(clienteActualizado.getClienteEstado());


        return clienteRepositorio.save(clienteExistente);
    }
}