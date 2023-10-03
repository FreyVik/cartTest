package com.gonzalo.cart.controller;

import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.service.CartService;
import com.gonzalo.cart.service.CartServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import static com.gonzalo.cart.modeltests.TestVariables.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @InjectMocks
    CartController cartController;

    @Mock
    CartServiceImp cartService;

    MockHttpSession session = new MockHttpSession();

    @Test
    @DisplayName("Should call to addProducts()")
    public void callAddProducts() {
        when(cartService.addProducts(SIMPLE_PRODUCT_LIST, session)).thenReturn(SIMPLE_CART);

        cartController.addProduct(SIMPLE_PRODUCT_DTO_LIST, session);

        verify(cartService, times(1)).addProducts(SIMPLE_PRODUCT_LIST, session);
    }

    @Test
    @DisplayName("Should call to getCartInfo()")
    public void callGetCartInfo() throws Exception {
        when(cartService.getCartInfo(1l, session)).thenReturn(SIMPLE_CART);

        cartController.getCart(1l, session);

        verify(cartService, times(1)).getCartInfo(1l, session);
    }

    @Test
    @DisplayName("Should call to deleteCart()")
    public void callDeleteCart() {
        cartController.deleteCart(session);

        verify(cartService, times(1)).deleteCart(session);
    }
}