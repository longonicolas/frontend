package com.accenture.ecommerce.models.entities.ventas;
import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado_venta")
public class EstadoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Estado estadoActual;

    @Enumerated(EnumType.STRING)
    private Estado estadoAnterior;

    public EstadoVenta() {
        fecha = LocalDate.now();
        estadoActual = Estado.A_PAGAR;
    }

    public EstadoVenta(Estado estadoNuevo, Estado estadoAnteriorNuevo) {
        fecha = LocalDate.now();
        estadoAnterior = estadoAnteriorNuevo;
        estadoActual = estadoNuevo;
    }

}
