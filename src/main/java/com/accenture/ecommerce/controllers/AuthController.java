package com.accenture.ecommerce.controllers;
import com.accenture.ecommerce.dtos.AuthRequestDTO;
import com.accenture.ecommerce.dtos.LoginResponseDTO;
import com.accenture.ecommerce.dtos.UsuarioOutputDTO;
import com.accenture.ecommerce.models.entities.usuarios.Usuario;
import com.accenture.ecommerce.services.usuarios.IUsuarioService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.accenture.ecommerce.auth.JwtUtil;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/users")
    public ResponseEntity<List<UsuarioOutputDTO>> todosLosUsuarios(){
        List<UsuarioOutputDTO> lista = usuarioService.buscarTodosLosUsuarios();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(authentication), "Login exitoso");
            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            LoginResponseDTO response = new LoginResponseDTO("Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequestDTO authRequest) {
        try {
            usuarioService.registrarUsuario(authRequest.getUsername(), authRequest.getPassword(), authRequest.getRol());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}