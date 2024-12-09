package com.accenture.ecommerce.services.pagos;
import com.accenture.ecommerce.clients.PagosClient;
import com.accenture.ecommerce.dtos.PagoRequestDTO;
import com.accenture.ecommerce.dtos.PagoResponseDTO;
import com.accenture.ecommerce.exceptions.APIPagosException;
import com.accenture.ecommerce.exceptions.PagoFalladoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcesamientoPagoAdapter implements IProcesamientoPagoAdapter {
    @Autowired
    PagosClient pagosClient;

    @Override
    public PagoResponseDTO procesarPago(PagoRequestDTO pago) {
        ResponseEntity<PagoResponseDTO> pagoResponse = null;
        try {
            pagoResponse = pagosClient.postPago(pago);
            if (pagoResponse.getStatusCode() == HttpStatus.CREATED || pagoResponse.getStatusCode() == HttpStatus.PAYMENT_REQUIRED) {
                return pagoResponse.getBody();
            } else {
                throw new APIPagosException("Error en comunicación con plataforma de pagos");
            }
        } catch (PagoFalladoException e) {
            return new PagoResponseDTO(false, null, "Pago fallido " + e.getMessage());
        } catch (APIPagosException e) {
            throw new APIPagosException("Error en comunicación con plataforma de pagos");
        }
    }
}
