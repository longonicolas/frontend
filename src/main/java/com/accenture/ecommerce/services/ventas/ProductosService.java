package com.accenture.ecommerce.services.ventas;
import com.accenture.ecommerce.dtos.ventasDtos.ProductoRequestDTO;
import com.accenture.ecommerce.exceptions.APIProductoNoEncontrado;
import com.accenture.ecommerce.models.entities.externos.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductosService {
    public Producto getProducto(ResponseEntity<ProductoRequestDTO> productoRequestDTO, Long productoId) {
        if (productoRequestDTO.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new APIProductoNoEncontrado("Producto #" + productoId + " no encontrado");
        }
        Long id = productoRequestDTO.getBody().getId();
        String nombre = productoRequestDTO.getBody().getNombre();
        Float precio = productoRequestDTO.getBody().getPrecioActual();
        return new Producto(id, nombre, precio);
    }
}
