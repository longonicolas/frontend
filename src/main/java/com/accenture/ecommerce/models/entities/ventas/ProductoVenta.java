package com.accenture.ecommerce.models.entities.ventas;
import com.accenture.ecommerce.models.entities.externos.Producto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id")
    private Venta venta;

    @Column
    private Integer cantidad;

    @Column
    private Float precioUnitario;

}
