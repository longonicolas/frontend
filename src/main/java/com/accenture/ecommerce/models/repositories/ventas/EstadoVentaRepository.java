package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.ventas.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoVentaRepository extends JpaRepository<EstadoVenta, Long> {



}