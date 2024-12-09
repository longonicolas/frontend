package com.accenture.ecommerce.models.entities.estadisticas;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface EstadisticasStrategy {
    public <T> List<T> generarInforme(LocalDate fechaInicio, LocalDate fechaFin, Pageable limite);
}