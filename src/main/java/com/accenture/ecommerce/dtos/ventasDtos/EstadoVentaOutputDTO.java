package com.accenture.ecommerce.dtos.ventasDtos;

import com.accenture.ecommerce.models.entities.ventas.EstadoVenta;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoVentaOutputDTO {

    String estadoVenta;
    Long idVenta;

    public EstadoVentaOutputDTO(EstadoVenta estado,Long idVenta){

        this.estadoVenta = estado.getEstadoActual().toString();
        this.idVenta = idVenta;
    }


}
