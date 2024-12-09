package com.accenture.ecommerce.exceptions;

public class FacturaNoPermitida extends RuntimeException {
    public FacturaNoPermitida(String message) {
        super(message);
    }
}
