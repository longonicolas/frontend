package com.accenture.ecommerce.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarritoNoEncontradoException.class)
    public ResponseEntity<String> handleCarritoNoEncontrado(CarritoNoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarritoProductoNoEncontradoException.class)
    public ResponseEntity<String> handleCarritoProductoNoEncontradoException(CarritoProductoNoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VentaNoEncontradaException.class)
    public ResponseEntity<String> handleVentaNoEncontrada(VentaNoEncontradaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FacturaNoPermitida.class)
    public ResponseEntity<String> handleFacturaNoPermitida(FacturaNoPermitida e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProductoYaAgregadoException.class)
    public ResponseEntity<String> handleProductoYaAgregadoException(ProductoYaAgregadoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CarritoDeshabilitadoException.class)
    public ResponseEntity<String> handleCarritoDeshabilitadoException(CarritoDeshabilitadoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CarritoVacioException.class)
    public ResponseEntity<String> handleCarritoVacioException(CarritoVacioException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(APIProductoNoEncontrado.class)
    public ResponseEntity<String> handleAPIProductoNoEncontrado(APIProductoNoEncontrado e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PagoFalladoException.class)
    public ResponseEntity<String> handlePagoFalladoException(PagoFalladoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(APIPagosException.class)
    public ResponseEntity<String> handleAPIPagosException(APIPagosException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}