package com.gonzalo.cart.model;

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
    private List<Product> products;
    private Integer totalProducts;
    private Double totalPrice;

    public Cart(Long id) {
        this.id = id + 1;
    }

    public void addProducts(List<Product> products) {
        if (this.products == null) {
            this.products = products;
        } else {
            products.forEach(product -> this.products.add(product));
        }

        this.completeData();
    }

    private void completeData() {
        this.totalProducts = this.products.size();
        this.totalPrice = this.products.stream().mapToDouble(Product::getAmount).sum();
    }
}
