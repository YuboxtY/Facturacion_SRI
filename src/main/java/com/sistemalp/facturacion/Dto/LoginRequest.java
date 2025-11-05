package com.sistemalp.facturacion.Dto;

import lombok.Data;

@Data

//dto sirve para transferir datos entre el cliente y el servidor,
// no todos los campos de la entidad usuario son necesarios para el login
public class LoginRequest {
    private String username;
    private String password;
}
