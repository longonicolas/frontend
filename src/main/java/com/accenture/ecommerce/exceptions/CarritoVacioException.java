package com.accenture.ecommerce.exceptions;

public class CarritoVacioException extends RuntimeException {
    public CarritoVacioException(String message) {
        super(message);
    }
}
