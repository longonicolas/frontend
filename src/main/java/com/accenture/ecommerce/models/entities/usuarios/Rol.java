package com.accenture.ecommerce.models.entities.usuarios;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private TipoRol nombre;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permiso> permisos = new HashSet<>();
}
