package com.sistemalp.facturacionsri.Dto;

import com.sistemalp.facturacionsri.Domain.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteConDocumneto {
    private Cliente cliente;
    private List<DocumetoListaClienteDto> documentos;
}
