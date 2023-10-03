package com.gonzalo.cart.modeltests;

import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Product;
import com.gonzalo.cart.model.dto.ProductListDTO;

import java.util.List;

public class TestVariables {

    public static Product PRODUCT_1 = Product.builder().id(1l).description("Product 1").amount(22.0d).build();
    public static Product PRODUCT_2 = Product.builder().id(2l).description("Product 2").amount(11.0d).build();

    public static List<Product> SIMPLE_PRODUCT_LIST = List.of(PRODUCT_1);
    public static List<Product> PRODUCTS_LIST = List.of(PRODUCT_1, PRODUCT_2);

    public static ProductListDTO SIMPLE_PRODUCT_DTO_LIST = ProductListDTO.builder()
            .products(SIMPLE_PRODUCT_LIST).build();

    public static Cart SIMPLE_CART = Cart.builder()
            .id(1l).products(SIMPLE_PRODUCT_LIST).totalProducts(1).totalPrice(22.0d)
            .build();
}
