package com.example.demo.ExceptionsHandler;

import com.example.demo.Dto.Error.ErrorDetail;
import com.example.demo.Dto.Error.GenericError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : result.getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append(", ");
        }
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // Eliminar la última coma y espacio

        logger.error(errorMessage.toString());
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
        logger.error(ex.getLocalizedMessage());

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

