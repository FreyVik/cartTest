package com.gonzalo.cart.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private Double amount;
}
