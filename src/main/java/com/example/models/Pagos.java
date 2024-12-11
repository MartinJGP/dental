package com.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cita_id")
    private Citas cita;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "servicio")
    private String servicio;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fechapago")
    private String fechaPago;

}
