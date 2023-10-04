package com.gonzalo.cart.controller;

import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.modeltests.TestVariables;
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

    TestVariables testVariables = new TestVariables();

    @Test
    @DisplayName("Should call to addProducts()")
    public void callAddProducts() {
        when(cartService.addProducts(testVariables.SIMPLE_PRODUCT_LIST())).thenReturn(testVariables.SIMPLE_CART());

        cartController.addProduct(testVariables.SIMPLE_PRODUCT_DTO_LIST());

        verify(cartService, times(1)).addProducts(testVariables.SIMPLE_PRODUCT_LIST());
    }

    @Test
    @DisplayName("Should call to getCartInfo()")
    public void callGetCartInfo() throws Exception {
        when(cartService.getCartInfo(1L)).thenReturn(testVariables.SIMPLE_CART());

        cartController.getCart(1L);

        verify(cartService, times(1)).getCartInfo(1L);
    }

    @Test
    @DisplayName("Should call to deleteCart()")
    public void callDeleteCart() {
        cartController.deleteCart();

        verify(cartService, times(1)).deleteCart();
    }
}