package com.accenture.ecommerce.dtos.ventasDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer stockActual;
    private Float precioActual;
    private String urlImagen;
    private Integer cantidad;
    private String categoria;
    private Long proveedorId;
}
