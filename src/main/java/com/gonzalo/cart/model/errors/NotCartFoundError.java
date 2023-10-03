package com.gonzalo.cart.model.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotCartFoundError {
    private Integer code;
    private Long cartId;
    private String description;
}
