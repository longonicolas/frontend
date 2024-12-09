package com.accenture.ecommerce.exceptions;

public class PagoFalladoException extends RuntimeException {
    public PagoFalladoException(String message) {
        super(message);
    }
}
