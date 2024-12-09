package com.accenture.ecommerce.models.entities.estadisticas;
import com.accenture.ecommerce.dtos.PersonaRankingOutputDTO;
import com.accenture.ecommerce.models.entities.estadisticas.EstadisticasStrategy;
import com.accenture.ecommerce.models.repositories.ventas.VentasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Component
public class MayoresVentasStrategy implements EstadisticasStrategy {
    private static final Logger logger = LoggerFactory.getLogger(MayoresVentasStrategy.class);
    @Autowired
    private VentasRepository ventasRepository;

    public List<PersonaRankingOutputDTO> generarInforme(LocalDate fechaInicio, LocalDate fechaFin, Pageable limite) {
        List<PersonaRankingOutputDTO> ranking = ventasRepository.findTopVendedores(fechaInicio, fechaFin, limite);
        logger.info("Ranking de vendedores:");
        ranking.forEach(dto -> logger.info(dto.toString()));
        return ranking;
    }
}