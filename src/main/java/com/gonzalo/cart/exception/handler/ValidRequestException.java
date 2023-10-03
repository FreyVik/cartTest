package com.gonzalo.cart.exception.handler;

import com.gonzalo.cart.model.errors.RequestValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidRequestException {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestValidationError> genericError(MethodArgumentNotValidException maex) {
        Map<String, String> errors = new HashMap<>();

        maex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        RequestValidationError customErrorMessage = RequestValidationError.builder().code(400).fieldErrors(errors).build();

        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }
}
