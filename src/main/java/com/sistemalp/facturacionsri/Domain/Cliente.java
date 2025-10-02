package com.sistemalp.facturacionsri.Domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.processing.Pattern;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 a 200 caracteres")
    @Column(length = 200, nullable = false)
    private String clienteNombre;
    private String clienteApellido;
    private String clienteDireccion;

    // @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe tener 10 dígitos")
    private String clienteTelefono;
    @Email(message = "El correo no es valido")
    private String clienteMail;

    private Integer clienteEstado;


}
