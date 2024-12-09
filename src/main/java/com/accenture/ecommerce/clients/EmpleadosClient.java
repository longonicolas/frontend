package com.accenture.ecommerce.clients;

import com.accenture.ecommerce.dtos.EmpleadoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "empleadosClient", url = "https://https://ceffeda5-bf2a-4056-bc01-0bc803b94c31.mock.pstmn.io/empleados")
public interface EmpleadosClient {

    @GetMapping("/{id}")
    ResponseEntity<EmpleadoRequestDTO> getEmpleado(@PathVariable("id") Long id);
}
