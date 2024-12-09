package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.externos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
