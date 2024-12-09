package com.accenture.ecommerce.clients;
import com.accenture.ecommerce.dtos.ventasDtos.ProductoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "productosClient", url = "https://9864c6fd-4f61-4188-9770-d434ca39c36d.mock.pstmn.io/productos")
public interface ProductosClient {
    @GetMapping("/{id}")
    ResponseEntity<ProductoRequestDTO> getProducto(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    ResponseEntity<Void> updateStockProducto(@PathVariable("id") Long id, @RequestParam("cantidad") Integer cantidadVendida);
}
