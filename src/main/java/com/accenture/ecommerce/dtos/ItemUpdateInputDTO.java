package com.accenture.ecommerce.dtos;
import lombok.Data;

@Data
public class ItemUpdateInputDTO {
    private Long itemId;
    private Integer nuevaCantidad;
}
