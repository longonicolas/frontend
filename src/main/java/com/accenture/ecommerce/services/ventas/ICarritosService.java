package com.accenture.ecommerce.services.ventas;
import com.accenture.ecommerce.dtos.*;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoNuevoInputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.CompraInputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.VentaOutputDTO;

public interface ICarritosService {
    public CarritoOutputDTO buscarCarrito(Long carritoId);
    public CarritoOutputDTO agregarCarrito(CarritoNuevoInputDTO carritoNuevoInputDTO);
    public void agregarProducto(Long carritoId, ItemNuevoInputDTO itemNuevoInputDTO); // TODO: cantidad > 0?
    public void actualizarCantidad(Long carritoId, ItemUpdateInputDTO itemUpdateInputDTO);
    public void eliminarProducto(Long carritoId, Long productoId); // TODO: carrito vacio?
    public void deshabilitarCarrito(Long carritoId);
    public void habilitarCarrito(Long carritoId);
    public VentaOutputDTO finalizarCompra(Long carritoId, CompraInputDTO compraInputDTO); // TODO: exito/error?
}