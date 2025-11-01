package com.sistemalp.facturacion.Entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min=3, max = 200, message = "El nombre debe tener entre 3 a 200 caracteres")
    @Column(length=200, nullable = false)
    private String clienteNombre;

    @Column(length=200)
    private String clienteApellido;
    @Column(length=300)
    private String clienteDireccion;

    @Pattern(regexp = "^[0-9]{10}$", message = "El numero de telefono debe tener 10 digitos")
    @Column(length=15)
    private String clienteTelefono; // Antes: regex de 9 dígitos

    @Email(message = "El correo no es valido")
    @Column(length=100)
    private String clienteMail;

    private Integer clienteEstado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TipoDocumentoCliente> documentos;
}