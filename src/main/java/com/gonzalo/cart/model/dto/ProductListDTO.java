package com.gonzalo.cart.model.dto;

import com.gonzalo.cart.model.Product;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
    @Valid
    private List<Product> products;
}
