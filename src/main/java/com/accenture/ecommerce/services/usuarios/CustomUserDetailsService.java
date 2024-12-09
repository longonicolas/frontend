package com.accenture.ecommerce.services.usuarios;
import com.accenture.ecommerce.models.entities.usuarios.Rol;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.models.repositories.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre().toString()));
            rol.getPermisos().forEach(permiso ->
                    authorities.add(new SimpleGrantedAuthority(permiso.getNombre())));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}