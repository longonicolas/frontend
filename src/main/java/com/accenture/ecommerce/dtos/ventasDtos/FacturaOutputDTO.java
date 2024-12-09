package com.accenture.ecommerce.dtos.ventasDtos;


import com.accenture.ecommerce.models.entities.ventas.Factura;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaOutputDTO {

    private Long id;
    private String fecha;
    private Long idVenta;

    public FacturaOutputDTO(Factura fact){

        this.id = fact.getId();
        this.fecha = fact.getFecha().toString();
        this.idVenta = fact.getVenta().getId();

    }

}
