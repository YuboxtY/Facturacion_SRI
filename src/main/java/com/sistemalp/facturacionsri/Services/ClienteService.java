package com.sistemalp.facturacionsri.Services;

import com.sistemalp.facturacionsri.Domain.Cliente;
import com.sistemalp.facturacionsri.Dto.ClienteConDocumneto;
import com.sistemalp.facturacionsri.Dto.DocumetoListaClienteDto;
import com.sistemalp.facturacionsri.Repositories.ClienteRepository;
import com.sistemalp.facturacionsri.Repositories.TipoDocumentoClienteRepository;
import com.sistemalp.facturacionsri.Repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

@Service
public class ClienteService {
    //Inyectar el repositorio de clientesRepositorio CRUD
    @Autowired
    private ClienteRepository clienteRepositorio;
@Autowired
private TipoDocumentoRepository tipoDocumentoRepository;
@Autowired
private TipoDocumentoClienteRepository tipoDocumentoClienteRepository;

    public Cliente guardar(ClienteConDocumneto clienteDto) {
        List<DocumetoListaClienteDto> documentos = clienteDto.getDocumentos();
        if (documentos == null || documentos.isEmpty()) {
            throw new RuntimeErrorException("errror");
        }
        Cliente cliente = clienteDto.getCliente();

        return null;

    }
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
