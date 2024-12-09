package com.accenture.ecommerce.models.repositories.ventas;
import com.accenture.ecommerce.models.entities.externos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {

}
