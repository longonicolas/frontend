package com.accenture.ecommerce.clients;
import com.accenture.ecommerce.dtos.PagoRequestDTO;
import com.accenture.ecommerce.dtos.PagoResponseDTO;
import com.accenture.ecommerce.dtos.ventasDtos.ProductoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagosClient", url = "https://9d916f07-906a-4241-8a68-50765bbd4a77.mock.pstmn.io", configuration = FeignConfig.class)
public interface PagosClient {
    @PostMapping
    ResponseEntity<PagoResponseDTO> postPago(@RequestBody PagoRequestDTO pago);
}
