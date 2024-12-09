package com.accenture.ecommerce.controllers;
import com.accenture.ecommerce.dtos.*;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoNuevoInputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.CarritoOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.CompraInputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.VentaOutputDTO;
import com.accenture.ecommerce.services.ventas.ICarritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/carritos")
public class CarritoController {
    @Autowired
    private ICarritosService carritosService;

    @GetMapping("/{carritoId}")
    public ResponseEntity<CarritoOutputDTO> buscarCarrito(@PathVariable Long carritoId) {
        return new ResponseEntity<>(this.carritosService.buscarCarrito(carritoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarritoOutputDTO> agregarCarrito(@RequestBody CarritoNuevoInputDTO carritoNuevoInputDTO) {
        return new ResponseEntity<>(this.carritosService.agregarCarrito(carritoNuevoInputDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{carritoId}/productos") // TODO: outputdto
    public ResponseEntity<Void> agregarProducto(@PathVariable Long carritoId, @RequestBody ItemNuevoInputDTO itemNuevoInputDTO) {
        this.carritosService.agregarProducto(carritoId, itemNuevoInputDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{carritoId}/productos")
    public ResponseEntity<Void> actualizarCantidad(@PathVariable Long carritoId, @RequestBody ItemUpdateInputDTO itemUpdateInputDTO) {
        this.carritosService.actualizarCantidad(carritoId, itemUpdateInputDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{carritoId}/productos/{itemId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long carritoId, @PathVariable Long itemId) {
        this.carritosService.eliminarProducto(carritoId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long carritoId) {
        this.carritosService.deshabilitarCarrito(carritoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{carritoId}/finalizacion")
    public ResponseEntity<VentaOutputDTO> finalizarCompra(@PathVariable Long carritoId, @RequestBody CompraInputDTO compraInputDTO) {
        VentaOutputDTO ventaDTO = this.carritosService.finalizarCompra(carritoId, compraInputDTO);
        this.carritosService.deshabilitarCarrito(carritoId);
        return new ResponseEntity<>(ventaDTO, HttpStatus.CREATED);
    }
}