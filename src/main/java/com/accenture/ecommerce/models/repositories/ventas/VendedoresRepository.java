package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.externos.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedoresRepository extends JpaRepository<Vendedor, Long> {
}
