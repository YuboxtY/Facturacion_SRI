package com.sistemalp.facturacion.Dto;

import lombok.Data;

@Data
public class LoginRequest { //este dto se usa para recibir los datos de login
    private String username;
    private String password;

}

