package com.accenture.ecommerce.dtos.ventasDtos;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import com.accenture.ecommerce.models.entities.ventas.Carrito;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CarritoOutputDTO {

    private Long id;
    private Boolean activo;
    private LocalDate fechaExpiracion;
    private Cliente cliente;
    private List<CarritoProductoDTO> productos;

    public CarritoOutputDTO(Carrito carrito) {
        this.id = carrito.getId();
        this.activo = carrito.getActivo();
        this.fechaExpiracion = carrito.getFechaExpiracion();
        this.cliente = carrito.getCliente();
        this.productos = carrito.getProductos() == null
                ? new ArrayList<>()
                : carrito.getProductos().stream().map(CarritoProductoDTO::new).collect(Collectors.toList());
    }
/*
    public Carrito dtoACarrito() {
        Carrito carrito = new Carrito();
        carrito.setId(this.id);
        carrito.setActivo(this.activo);
        carrito.setFechaExpiracion(this.fechaExpiracion);
        carrito.setProductos(this.getProductos() == null
                ? new ArrayList<>()
                : this.getProductos().stream().map(p -> new CarritoProducto(p.getId(), p.getProducto(), p.getCantidad())).collect(Collectors.toList()));
        return carrito;
    }*/

}