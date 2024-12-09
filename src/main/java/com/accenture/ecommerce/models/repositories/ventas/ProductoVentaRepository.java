package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.ventas.ProductoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, Long> {
}
