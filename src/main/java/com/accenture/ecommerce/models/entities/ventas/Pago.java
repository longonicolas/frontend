package com.accenture.ecommerce.models.entities.ventas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MedioDePago medioDePago;

    @Column(name = "fecha_procesamiento")
    private LocalDate fechaProcesamiento;

    @Column(name = "transaccion_id")
    private String transaccionId;

    @Column
    private Float importe;

    public Pago(MedioDePago medioDePago, LocalDate now, String transaccionId, Float importe) {
        this.medioDePago = medioDePago;
        this.fechaProcesamiento = now;
        this.transaccionId = transaccionId;
        this.importe = importe;
    }
}
