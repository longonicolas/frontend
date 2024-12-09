package com.accenture.ecommerce.models.repositories.usuarios;
import com.accenture.ecommerce.models.entities.usuarios.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {}