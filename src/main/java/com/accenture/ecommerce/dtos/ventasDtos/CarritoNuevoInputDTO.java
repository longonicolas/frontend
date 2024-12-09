package com.accenture.ecommerce.dtos.ventasDtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CarritoNuevoInputDTO {
    private Long clienteId;
    private String nombre;
}
