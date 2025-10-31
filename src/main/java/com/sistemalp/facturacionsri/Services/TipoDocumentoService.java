package com.sistemalp.facturacionsri.Services;

import com.sistemalp.facturacionsri.Domain.Cliente;
import com.sistemalp.facturacionsri.Domain.TipoDocumento;
import com.sistemalp.facturacionsri.Domain.TipoDocumentoCliente;
import com.sistemalp.facturacionsri.Repositories.ClienteRepository;
import com.sistemalp.facturacionsri.Repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoService {
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    public TipoDocumento guardar( TipoDocumento TipoDocumento) {
        return tipoDocumentoRepository.save(TipoDocumento);
    }

    //Listar todos los clientes
    public List<TipoDocumento> listarAll() {
        return tipoDocumentoRepository.findAll();
    }

    //Buscar un cliente por ID
    public TipoDocumento buscarId(Long id) {
        return tipoDocumentoRepository.findById(id).orElse(null);
    }

    //Eliminar un cliente
    public void eliminar(Long id) {
        tipoDocumentoRepository.deleteById(id);
    }

}
