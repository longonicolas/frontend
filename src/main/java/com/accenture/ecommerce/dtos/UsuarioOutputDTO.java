package com.accenture.ecommerce.dtos;

import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutputDTO {

    Long id;
    String nombre;

    public UsuarioOutputDTO(Usuario usuario) {

        this.id = usuario.getId();
        this.nombre = usuario.getUsername();
    }
}
