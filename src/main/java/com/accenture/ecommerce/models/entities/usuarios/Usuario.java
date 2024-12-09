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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
