package com.example.demo.ExceptionsHandler;

import com.example.demo.Dtos.Error.ErrorDetail;
import com.example.demo.Dtos.Error.GenericError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : result.getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append(", ");
        }
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // Eliminar la última coma y espacio

        return ResponseEntity.badRequest().body(
                new GenericError(
                        new ErrorDetail(
                                HttpStatus.BAD_REQUEST.value(),
                                errorMessage.toString()
                        )
                )
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GenericError> handleException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GenericError(
                        new ErrorDetail(
                                HttpStatus.CONFLICT.value(),
                                "Email ya registrado"
                        )
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericError> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body(
                new GenericError(
                        new ErrorDetail(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Se produjo un error en el servidor"
                        )
                )
        );
    }
}

