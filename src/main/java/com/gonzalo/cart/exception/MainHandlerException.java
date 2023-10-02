package com.gonzalo.cart.exception;

import com.gonzalo.cart.controller.CartController;
import com.gonzalo.cart.model.errors.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainHandlerException {

    Logger log = LoggerFactory.getLogger(MainHandlerException.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> genericError(RuntimeException e) {
        ResponseError customErrorMessage = ResponseError.builder().code(500).description("Unexpected error").build();

        log.error("Error", e);

        return new ResponseEntity<>(customErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
