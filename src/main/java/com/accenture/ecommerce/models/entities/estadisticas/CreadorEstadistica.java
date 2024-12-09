package com.accenture.ecommerce.models.entities.estadisticas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreadorEstadistica {
    private EstadisticasStrategy estrategia;

    public <T> List<T> crearEstadistica(LocalDate fechaInicial, LocalDate fechaFinal, Integer cantidad) {
        Pageable pageable = PageRequest.of(0, cantidad);
        return estrategia.generarInforme(fechaInicial, fechaFinal, pageable);
    }
}