package com.accenture.ecommerce.services.pagos;
import com.accenture.ecommerce.dtos.PagoRequestDTO;
import com.accenture.ecommerce.dtos.PagoResponseDTO;
import com.accenture.ecommerce.exceptions.PagoFalladoException;
import com.accenture.ecommerce.models.entities.ventas.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProcesamientoPagoService {
    @Autowired
    ProcesamientoPagoAdapter procesamientoPagoAdapter;

    public String procesarPago(Float importe) {
        PagoRequestDTO pagoRequestDTO = new PagoRequestDTO("", importe);
        PagoResponseDTO pagoResponseDTO = procesamientoPagoAdapter.procesarPago(pagoRequestDTO);
        if (pagoResponseDTO.isSuccess()) {
            return pagoResponseDTO.getTransaccionId();
        } else {
            throw new PagoFalladoException(pagoResponseDTO.getError());
        }
    }
}
