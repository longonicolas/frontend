package com.accenture.ecommerce.dtos;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ListaPersonaRankingOutputDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<PersonaRankingOutputDTO> personas;
}
