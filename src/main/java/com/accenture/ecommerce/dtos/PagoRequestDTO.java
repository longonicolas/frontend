package com.accenture.ecommerce.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {
    private String detallePago;
    private Float importe;
}
