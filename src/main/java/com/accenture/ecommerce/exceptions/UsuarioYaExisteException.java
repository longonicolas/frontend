package com.accenture.ecommerce.exceptions;

public class UsuarioYaExisteException extends RuntimeException {
  public UsuarioYaExisteException(String message) {
    super(message);
  }
}
