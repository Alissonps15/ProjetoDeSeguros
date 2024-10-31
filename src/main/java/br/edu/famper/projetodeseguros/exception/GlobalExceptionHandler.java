package br.edu.famper.projetodeseguros.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse erroDetails = new ErrorResponse(
                new Date(),
                HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(),
                request.getDescription(false)

        );
        return new ResponseEntity<>(erroDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception ex, WebRequest request) {
        ErrorResponse erroDetails = new ErrorResponse(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(erroDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
