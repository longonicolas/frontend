package com.accenture.ecommerce.clients;
import com.accenture.ecommerce.exceptions.APIPagosException;
import com.accenture.ecommerce.exceptions.PagoFalladoException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 500) {
            return new APIPagosException("Error en comunicación con plataforma de pagos");
        }
        if (response.status() == 402) {
            return new PagoFalladoException("");
        }
        return new Default().decode(methodKey, response);
    }
}