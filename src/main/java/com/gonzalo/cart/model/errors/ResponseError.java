package com.gonzalo.cart.model.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseError {
    private Integer code;
    private String description;
}
