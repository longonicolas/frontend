package com.accenture.ecommerce.services.ventas;


import com.accenture.ecommerce.dtos.EmpleadoRequestDTO;
import com.accenture.ecommerce.dtos.ventasDtos.ProductoRequestDTO;
import com.accenture.ecommerce.exceptions.APIProductoNoEncontrado;
import com.accenture.ecommerce.models.entities.externos.Producto;
import com.accenture.ecommerce.models.entities.externos.Vendedor;
import com.accenture.ecommerce.exceptions.APIEmpleadoNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    public Vendedor getEmpleado(ResponseEntity<EmpleadoRequestDTO> empleadoRequestDTO) {
        if (empleadoRequestDTO.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new APIEmpleadoNoEncontrado("Empleado no encontrado");
        }
        Long id = empleadoRequestDTO.getBody().getIdEmpleado();
        String nombre = empleadoRequestDTO.getBody().getNombre();
        return new Vendedor(id, nombre);
    }
}
