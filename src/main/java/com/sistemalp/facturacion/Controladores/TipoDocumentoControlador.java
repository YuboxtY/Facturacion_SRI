package com.sistemalp.facturacion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemalp.facturacion.Entidades.TipoDocumento;
import com.sistemalp.facturacion.Servicios.TipoDocumentoServicio;

@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoControlador {
    @Autowired
    private TipoDocumentoServicio tipoDocumentoServicio;

    @PostMapping
    public TipoDocumento guardar(@RequestBody TipoDocumento tipoDocumento){
        return tipoDocumentoServicio.guardar(tipoDocumento);
    }
    @GetMapping
    public List<TipoDocumento> listaAll(){
        return tipoDocumentoServicio.listarAll();
    }
       
}
