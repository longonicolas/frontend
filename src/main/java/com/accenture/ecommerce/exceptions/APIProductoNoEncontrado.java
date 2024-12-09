package com.accenture.ecommerce.exceptions;

public class APIProductoNoEncontrado extends RuntimeException {
    public APIProductoNoEncontrado(String message) {
        super(message);
    }
}
