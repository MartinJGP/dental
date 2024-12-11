package com.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 6)
    @Column(unique = true)
    private String codigointerno;

    @NotBlank
    @Size(max = 50)
    private String nombres;

    @NotBlank
    @Size(max = 50)
    private String apellidos;

    @NotBlank
    @Size(max = 30)
    private String telefono;

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;

    private boolean estado;


    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}