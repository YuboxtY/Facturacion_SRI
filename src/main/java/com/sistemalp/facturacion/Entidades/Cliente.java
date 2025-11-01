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

    // --- ARREGLADO (Error de tipeo) ---
    @Column(length=200)
    private String clienteApellido; // Antes: clienteAplellido

    // --- ARREGLADO (Error de tipeo) ---
    @Column(length=300)
    private String clienteDireccion; // Antes: clienteDirecion

    // --- ARREGLADO (Error de lógica) ---
    // El regex ahora acepta 10 dígitos, como decía tu mensaje.
    @Pattern(regexp = "^[0-9]{10}$", message = "El numero de telefono debe tener 10 digitos")
    @Column(length=15)
    private String clienteTelefono; // Antes: regex de 9 dígitos

    @Email(message = "El correo no es valido")
    @Column(length=100)
    private String clienteMail;

    private Integer clienteEstado;

    // --- ELIMINADO (¡Este era el error!) ---
    // Este campo causaba el 'ConstraintViolationException'.
    // Tu lógica de servicio (correctamente) guarda el documento
    // en la tabla 'TipoDocumentoCliente', no aquí.
    /*
    @NotBlank(message = "La cédula/RUC no puede estar vacio")
    @Size(min=10, max = 13, message = "El documento debe tener 10 o 13 caracteres")
    @Column(length=13, nullable = false, unique = true)
    private String clienteCedula;
    */

    // --- CORRECTO ---
    // Esta es la relación que tu servicio SÍ utiliza.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TipoDocumentoCliente> documentos;
}