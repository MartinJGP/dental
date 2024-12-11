package com.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "citas")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "horario_id")
    private HorarioAtencion horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @NotBlank
    @Size(max = 50)
    private String nombresapellidos;

    @Column(name = "edad")
    private Long edad;

    @Size(max = 20)
    private String numeroCelular;
    @NotBlank
    @Size(max = 15)
    private String documento;
    @NotBlank
    @Size(max = 50)
    private String especialidad;


    private String comentarios;

    private Boolean estado;


}
