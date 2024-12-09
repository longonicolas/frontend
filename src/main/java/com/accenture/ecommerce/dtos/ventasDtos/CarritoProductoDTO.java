package com.accenture.ecommerce.dtos.ventasDtos;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.ventas.CarritoProducto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoProductoDTO {

    Long id;
    Producto producto;
    Integer cantidad;

    public CarritoProductoDTO(CarritoProducto carritoProducto) {
        this.id = carritoProducto.getId();
        this.producto = carritoProducto.getProducto();
        this.cantidad = carritoProducto.getCantidad();
    }

}