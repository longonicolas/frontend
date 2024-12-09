package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.dtos.PersonaRankingOutputDTO;
import com.accenture.ecommerce.models.entities.ventas.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentasRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT new com.accenture.ecommerce.dtos.PersonaRankingOutputDTO(v.vendedor.nombre, COUNT(v)) " +
            "FROM Venta v " +
            "WHERE v.fechaGeneracion BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY v.vendedor.nombre " +
            "ORDER BY COUNT(v) DESC")
    public List<PersonaRankingOutputDTO> findTopVendedores(@Param("fechaInicio") LocalDate fechaInicio,
                                                           @Param("fechaFin") LocalDate fechaFin,
                                                           Pageable pageable);
}