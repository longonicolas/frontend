package com.accenture.ecommerce;
import com.accenture.ecommerce.models.entities.usuarios.TipoRol;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.models.repositories.usuarios.UsuarioRepository;
import com.accenture.ecommerce.services.usuarios.UsuarioService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    //@Disabled
    @Test
    void testSignupSuccess() throws Exception {
        String requestBody = """
            {
                "username": "user",
                "password": "123",
                "rol": "CLIENTE"
            }
        """;

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isCreated());

        Optional<Usuario> savedUser = usuarioRepository.findByUsername("user");
        assertTrue(savedUser.isPresent(), "No se encontró el usuario en el repositorio");
        assertEquals("user", savedUser.get().getUsername(), "El nombre de usuario no coincide");
        assertEquals(TipoRol.CLIENTE, savedUser.get().getRoles().stream().filter(r -> r.getNombre().equals(TipoRol.CLIENTE)).findFirst().get().getNombre(), "The role should match");
    }

    //@Disabled
    @Test
    void testLoginSuccess() throws Exception {
        usuarioService.registrarUsuario("user", "123", "CLIENTE");

        String requestBody = """
            {
                "username": "user",
                "password": "123"
            }
        """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
}
