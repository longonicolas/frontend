package com.accenture.ecommerce.exceptions;

public class CarritoProductoNoEncontradoException extends RuntimeException {

    public CarritoProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}
