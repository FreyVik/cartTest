package com.gonzalo.cart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotCartFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Long cartId;

    public NotCartFoundException(Long cartId) {
        this.cartId = cartId;
    }
}
