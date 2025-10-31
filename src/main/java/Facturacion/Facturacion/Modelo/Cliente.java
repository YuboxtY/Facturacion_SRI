package Facturacion.Facturacion.Modelo;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

}
