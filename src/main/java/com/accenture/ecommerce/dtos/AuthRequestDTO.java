package com.accenture.ecommerce.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotBlank(message = "Nombre de usuario requerido")
    private String username;
    @NotBlank(message = "Contraseña requerida")
    private String password;
    @NotBlank(message = "Rol requerido")
    @Pattern(regexp = "^(CLIENTE|ORGANIZACION|EMPLEADO)$", message = "Rol inválido")
    private String rol;
}
