package com.accenture.ecommerce.services.pagos;
import com.accenture.ecommerce.dtos.PagoRequestDTO;
import com.accenture.ecommerce.dtos.PagoResponseDTO;

public interface IProcesamientoPagoAdapter {
    PagoResponseDTO procesarPago(PagoRequestDTO pago);
}
