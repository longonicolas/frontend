package com.accenture.ecommerce.models.entities.estadisticas;
import com.accenture.ecommerce.dtos.PersonaRankingOutputDTO;
import com.accenture.ecommerce.models.entities.estadisticas.EstadisticasStrategy;
import com.accenture.ecommerce.models.repositories.ventas.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Component
public class MayoresComprasStrategy implements EstadisticasStrategy {
    @Autowired
    VentasRepository ventasRepository;

    public List<PersonaRankingOutputDTO> generarInforme(LocalDate fechaInicio, LocalDate fechaFin, Pageable limite) {
        return null;
    }
}