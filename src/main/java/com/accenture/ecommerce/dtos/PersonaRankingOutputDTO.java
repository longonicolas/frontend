package com.accenture.ecommerce.dtos;
import lombok.Data;

@Data
public class PersonaRankingOutputDTO {
    private String nombre;
    private Long cantidad;

    public PersonaRankingOutputDTO(String nombre, Long cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    public String toString() {
        return "PersonaRankingOutputDTO{" +
                "vendedor='" + nombre + '\'' +
                ", numeroVentas=" + cantidad +
                '}';
    }
}
