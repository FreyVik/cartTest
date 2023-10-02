package com.gonzalo.cart.model;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListDTO {
    @Valid
    private List<Product> products;
}
