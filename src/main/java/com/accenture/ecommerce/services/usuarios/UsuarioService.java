package com.accenture.ecommerce.services.usuarios;
import com.accenture.ecommerce.dtos.UsuarioOutputDTO;
import com.accenture.ecommerce.dtos.ventasDtos.VentaOutputDTO;
import com.accenture.ecommerce.exceptions.UsuarioYaExisteException;
import com.accenture.ecommerce.models.entities.usuarios.Rol;
import com.accenture.ecommerce.models.entities.usuarios.TipoRol;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.models.repositories.usuarios.RolRepository;
import com.accenture.ecommerce.models.repositories.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements  IUsuarioService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    public List<UsuarioOutputDTO> buscarTodosLosUsuarios(){
        return usuarioRepository.findAll().stream().map(UsuarioOutputDTO::new).toList();
    }

    public void registrarUsuario(String username, String plainPassword, String rolStr) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new UsuarioYaExisteException("El nombre de usuario ya está en uso");
        } else {
            String encodedPassword = passwordEncoder.encode(plainPassword);
            Usuario user = new Usuario(username, encodedPassword);
            usuarioRepository.save(user);
            agregarRol(username, rolStr);
        }
    }

    public void agregarRol(String username, String rolStr) {
        Optional<Usuario> user = usuarioRepository.findByUsername(username);
        if (user.isPresent()) {
            Usuario usuario = user.get();
            Rol rol = rolRepository.findByNombre(TipoRol.valueOf(rolStr.toUpperCase()));
            usuario.getRoles().add(rol); // TODO: verificar existencia de rol
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}
