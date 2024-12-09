package com.accenture.ecommerce.dtos;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PorcentajeRankingOutputDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Float porcentaje;
}
