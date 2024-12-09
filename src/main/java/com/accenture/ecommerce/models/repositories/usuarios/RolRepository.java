package com.accenture.ecommerce.models.repositories.usuarios;
import com.accenture.ecommerce.models.entities.usuarios.Rol;
import com.accenture.ecommerce.models.entities.usuarios.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByNombre(TipoRol nombre);
}