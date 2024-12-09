package com.accenture.ecommerce.auth;
import com.accenture.ecommerce.models.entities.usuarios.Permiso;
import com.accenture.ecommerce.models.entities.usuarios.Rol;
import com.accenture.ecommerce.models.entities.usuarios.TipoRol;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.repositories.usuarios.PermisoRepository;
import com.accenture.ecommerce.models.repositories.usuarios.RolRepository;
import com.accenture.ecommerce.models.repositories.usuarios.UsuarioRepository;
import com.accenture.ecommerce.models.repositories.ventas.ProductosRepository;
import com.accenture.ecommerce.services.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductosRepository productosRepository;


    @Override
    public void run(String... args) throws Exception {
        Permiso p1 = new Permiso("AGREGAR_PRODUCTO");
        Permiso p2 = new Permiso("FINALIZAR_COMPRA");
        permisoRepository.saveAll(List.of(p1, p2));

        Set<Permiso> todosLosPermisos = new HashSet<>(permisoRepository.findAll());
        Rol rolAdmin = rolRepository.findByNombre(TipoRol.ADMIN);
        if (rolAdmin == null) {
            rolAdmin = new Rol();
            rolAdmin.setNombre(TipoRol.ADMIN);
            rolAdmin.setPermisos(todosLosPermisos);
            rolRepository.save(rolAdmin);
        }
        Rol rolCliente = rolRepository.findByNombre(TipoRol.CLIENTE);
        if (rolCliente == null) {
            rolCliente = new Rol();
            rolCliente.setNombre(TipoRol.CLIENTE);
            rolRepository.save(rolCliente);
        }

        usuarioService.registrarUsuario("admin", "admin", "ADMIN");
        usuarioService.agregarRol("admin", "CLIENTE");

        Producto cocaCola = new Producto(1L, "Coca Cola", 1150F);
        Producto sevenUP = new Producto(2L, "Seven Up", 1000F);
        Producto fernetBranca = new Producto(3L, "Fernet Branca", 10500F);
        productosRepository.save(sevenUP);
        productosRepository.save(cocaCola);
        productosRepository.save(fernetBranca);
    }
}
