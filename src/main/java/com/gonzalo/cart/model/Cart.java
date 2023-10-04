package com.gonzalo.cart.model;

import com.gonzalo.cart.models.ProductDTO;
import jakarta.servlet.http.HttpSession;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Long id;
    private List<ProductDTO> products;
    private Integer totalProducts;
    private Double totalPrice;

    public Cart(Long id) {
        this.id = id + 1;
    }

    public void addProducts(List<ProductDTO> products) {
        if (this.products == null) {
            this.products = products;
        } else {
            products.forEach(p -> this.products.add(p));
        }

        this.completeData();
    }

    private void completeData() {
        this.totalProducts = this.products.size();
        this.totalPrice = this.products.stream().mapToDouble(ProductDTO::getAmount).sum();
    }
}
