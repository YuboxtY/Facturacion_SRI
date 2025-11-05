package com.sistemalp.facturacion.Dto;

import lombok.Data;

@Data

//JWT sirve para transferir el token generado al cliente despues de un login exitoso
//en el token viaja el username y el token generado
public class JwtResponse {
    private String token;
    private String username;
}
