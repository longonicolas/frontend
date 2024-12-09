package com.accenture.ecommerce.dtos.ventasDtos;
import com.accenture.ecommerce.models.entities.ventas.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class VentaOutputDTO {

    private Long id;
    private String cliente;
    private String vendedor;
    private Estado estadoActual;
    private Set<ProductoVentaDTO> productos;
    private LocalDate fechaGeneracion;
    private LocalDate fechaEntrega;
    private Long pagoId;
    private Float importe;

    public VentaOutputDTO(Venta venta) {
        this.id = venta.getId();
        this.cliente = venta.getCliente().getNombre();
        this.vendedor = venta.getVendedor().getNombre();
        this.estadoActual = venta.getEstadoVenta().getEstadoActual();
        this.productos = venta.getProductos() == null
                ? new HashSet<>()
                : venta.getProductos().stream().map(ProductoVentaDTO::new).collect(Collectors.toSet());
        this.fechaGeneracion = venta.getFechaGeneracion();
        this.fechaEntrega = venta.getFechaEntrega();
        this.pagoId = venta.getPago().getId();
        this.importe = venta.getImporte();
    }

}
