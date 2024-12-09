package com.accenture.ecommerce.dtos.ventasDtos;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.ventas.ProductoVenta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductoVentaDTO {

    private Long id;
    private Producto producto;
    private Integer cantidad;
    private Float precioUnitario;

    public ProductoVentaDTO(ProductoVenta producto) {
        this.id = producto.getId();
        this.producto = producto.getProducto();
        this.cantidad = producto.getCantidad();
        this.precioUnitario = producto.getPrecioUnitario();
    }

}
