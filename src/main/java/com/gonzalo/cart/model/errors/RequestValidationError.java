package com.gonzalo.cart.model.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Setter
@Getter
public class RequestValidationError {
    private Integer code;
    private Map<String, String> fieldErrors;
}
