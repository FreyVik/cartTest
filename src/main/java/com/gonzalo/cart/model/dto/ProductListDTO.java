package com.gonzalo.cart.model.dto;

import com.gonzalo.cart.model.Product;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductListDTO {
    @Valid
    private List<Product> products;
}
