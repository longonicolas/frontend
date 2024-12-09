package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.ventas.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritosRepository extends JpaRepository<Carrito, Long> {



}