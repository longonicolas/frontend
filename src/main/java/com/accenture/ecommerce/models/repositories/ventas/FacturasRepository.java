package com.accenture.ecommerce.models.repositories.ventas;

import com.accenture.ecommerce.models.entities.ventas.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturasRepository extends JpaRepository<Factura, Long> {

}
