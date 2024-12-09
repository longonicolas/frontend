package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.ventas.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagosRepository extends JpaRepository<Pago, Long> {
}
