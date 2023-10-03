package com.gonzalo.cart.exception.handler;

import com.gonzalo.cart.exception.NotCartFoundException;
import com.gonzalo.cart.model.errors.NotCartFoundError;
import com.gonzalo.cart.model.errors.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainHandlerException {

    @Value("${description.cart.not.found}")
    private String cartNotFoundDescription;

    Logger log = LoggerFactory.getLogger(MainHandlerException.class);

    @ExceptionHandler(NotCartFoundException.class)
    public ResponseEntity<NotCartFoundError> noCartError(NotCartFoundException ncfe) {
        log.error("NotCartFoundException error:\n", ncfe);

        NotCartFoundError errorResponse = NotCartFoundError.builder()
                .cartId(ncfe.getCartId())
                .code(404)
                .description(cartNotFoundDescription + " " + ncfe.getCartId())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> genericError(RuntimeException e) {
        ResponseError customErrorMessage = ResponseError.builder().code(500).description("Unexpected error").build();

        log.error("Error", e);

        return new ResponseEntity<>(customErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
