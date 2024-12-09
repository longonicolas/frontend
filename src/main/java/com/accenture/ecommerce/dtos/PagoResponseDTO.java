package com.accenture.ecommerce.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private boolean success;
    private String transaccionId;
    private String error;
}
