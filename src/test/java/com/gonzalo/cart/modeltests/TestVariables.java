package com.gonzalo.cart.modeltests;

import com.gonzalo.cart.models.CartDTO;
import com.gonzalo.cart.models.ProductListDTO;
import com.gonzalo.cart.models.ProductDTO;

import java.util.List;

public class TestVariables {

    private final ProductDTO productDTO1 = new ProductDTO();
    private final ProductDTO productDTO2 = new ProductDTO();

    private final CartDTO cart = new CartDTO();

    private final ProductListDTO productListDTO = new ProductListDTO();

    public TestVariables() {
        productDTO1.setId(1L);
        productDTO1.setDescription("Product 1");
        productDTO1.amount(22.0d);

        productDTO2.setId(2L);
        productDTO2.setDescription("Product 2");
        productDTO2.amount(11.0d);

        cart.setId(1L);
        cart.setProducts(List.of(PRODUCT_1()));
        cart.setTotalProducts(1);
        cart.setTotalPrice(22.0d);
    }

    public ProductDTO PRODUCT_1() {
        return productDTO1;
    }

    public ProductDTO PRODUCT_2() {
        return productDTO2;
    }

    public CartDTO SIMPLE_CART() {
        return cart;
    }

    public ProductListDTO SIMPLE_PRODUCT_DTO_LIST() {
        productListDTO.setProducts(List.of(PRODUCT_1()));
        return productListDTO;
    }

    public List<ProductDTO> SIMPLE_PRODUCT_LIST() {
        return List.of(PRODUCT_1());
    }
}
