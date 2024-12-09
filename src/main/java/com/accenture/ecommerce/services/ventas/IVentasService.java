package com.accenture.ecommerce.services.ventas;
import com.accenture.ecommerce.dtos.ventasDtos.CompraInputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.FacturaOutputDTO;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import com.accenture.ecommerce.models.entities.ventas.Carrito;
import com.accenture.ecommerce.dtos.ventasDtos.VentaOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.EstadoVentaOutputDTO;
import com.accenture.ecommerce.models.entities.ventas.CarritoProducto;
import com.accenture.ecommerce.models.entities.ventas.ProductoVenta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IVentasService {

    public List<VentaOutputDTO> buscarTodasLasVentas();
    public VentaOutputDTO buscarVenta(Long id);
    public VentaOutputDTO crearVenta(Carrito unCarrito, Cliente cliente, CompraInputDTO compra);
    public ProductoVenta convertirCarritoProductoAProducto(CarritoProducto carritoProducto);
    public FacturaOutputDTO generarFactura(Long id);
    public EstadoVentaOutputDTO buscarEstadoVenta(Long id);
    public void cancelarVenta(Long id);
}
