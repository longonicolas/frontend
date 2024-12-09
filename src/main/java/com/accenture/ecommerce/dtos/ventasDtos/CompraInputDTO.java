package com.accenture.ecommerce.dtos.ventasDtos;
import com.accenture.ecommerce.models.entities.ventas.MedioDePago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraInputDTO {
    MedioDePago medioDePago;
}
