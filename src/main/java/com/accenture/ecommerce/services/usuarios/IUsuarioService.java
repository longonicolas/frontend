package com.accenture.ecommerce.services.usuarios;

import com.accenture.ecommerce.dtos.UsuarioOutputDTO;
import com.accenture.ecommerce.models.entities.usuarios.Rol;

import java.util.List;

public interface IUsuarioService {

    public List<UsuarioOutputDTO> buscarTodosLosUsuarios();
        public void registrarUsuario(String username, String plainPassword, String rol);
    public void agregarRol(String username, String rol);


    }