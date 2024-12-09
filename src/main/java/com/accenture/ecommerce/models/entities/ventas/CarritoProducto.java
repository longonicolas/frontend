package com.accenture.ecommerce.models.entities.ventas;
import com.accenture.ecommerce.models.entities.externos.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carrito_producto")
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id", nullable = false)
    Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
    Producto producto;

    @Column
    Integer cantidad;

    public CarritoProducto(Carrito carrito, Producto producto, Integer cantidad) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public CarritoProducto() {
        this.producto = new Producto();
        this.cantidad = 0;
    }

}