package com.gonzalo.cart.service;

import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.models.CartDTO;
import com.gonzalo.cart.modeltests.TestVariables;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import static com.gonzalo.cart.model.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImpTest {

    @InjectMocks
    CartServiceImp cartService;

    @Mock
    HttpSession session;

    TestVariables testVariables = new TestVariables();

    @Test
    @DisplayName("Should add products to a cart")
    public void addProducts() {
        when(session.getAttribute(CART_ATTRIBUTE)).thenReturn(testVariables.SIMPLE_CART());

        CartDTO cartResult = cartService.addProducts(anyList());

        assertEquals(testVariables.SIMPLE_CART(), cartResult);
        verify(session, times(1)).getAttribute(CART_ATTRIBUTE);
    }

    @Test
    @DisplayName("Should get the cart info")
    public void getCartInfo() throws Exception {
        when(session.getAttribute(CART_ATTRIBUTE)).thenReturn(testVariables.SIMPLE_CART());

        CartDTO cartResult = cartService.getCartInfo(1L);

        assertEquals(testVariables.SIMPLE_CART(), cartResult);
        verify(session, times(1)).getAttribute(CART_ATTRIBUTE);
    }
}