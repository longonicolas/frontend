package com.accenture.ecommerce.controllers;
import com.accenture.ecommerce.dtos.ventasDtos.FacturaOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.VentaOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.EstadoVentaOutputDTO;
import com.accenture.ecommerce.services.ventas.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private IVentasService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaOutputDTO>> todasLasVentas() {
        return new ResponseEntity<>(this.ventaService.buscarTodasLasVentas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaOutputDTO> buscarVenta(@PathVariable Long id) {
        return new ResponseEntity<>(this.ventaService.buscarVenta(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // TODO
    public ResponseEntity<Void> cancelarVenta(@PathVariable Long id) {
        this.ventaService.cancelarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/estados")
    public ResponseEntity<EstadoVentaOutputDTO> chequearEstadoVenta(@PathVariable Long id) {
        return new ResponseEntity<>(this.ventaService.buscarEstadoVenta(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/facturas")
    public ResponseEntity<FacturaOutputDTO> solicitarFactura(@PathVariable Long id) {
        return new ResponseEntity<>(this.ventaService.generarFactura(id), HttpStatus.CREATED);
    }
}
