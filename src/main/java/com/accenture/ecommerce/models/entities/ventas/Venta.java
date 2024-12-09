package com.accenture.ecommerce.models.entities.ventas;
import com.accenture.ecommerce.models.entities.externos.*;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "estado_venta_id", referencedColumnName = "id")
    private EstadoVenta estadoVenta;

    @OneToMany
    private List<ProductoVenta> productos;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @OneToOne
    @JoinColumn(name = "pago_id", referencedColumnName = "id")
    private Pago pago;

    @Column
    private Float importe;
}
