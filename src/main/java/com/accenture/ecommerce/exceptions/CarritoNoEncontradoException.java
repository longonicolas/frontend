package com.accenture.ecommerce.exceptions;

public class CarritoNoEncontradoException extends RuntimeException {

    public CarritoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}