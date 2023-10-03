package com.gonzalo.cart.service;

import com.gonzalo.cart.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import static com.gonzalo.cart.model.Constants.*;
import static com.gonzalo.cart.modeltests.TestVariables.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImpTest {

    @InjectMocks
    CartServiceImp cartService;

    @Mock
    Cart cart;

    @Mock
    HttpSession session;

    @Test
    @DisplayName("Should add products to a cart")
    public void addProducts() {
        when(session.getAttribute(CART_ATTRIBUTE)).thenReturn(SIMPLE_CART);

        Cart cartResult = cartService.addProducts(anyList(), session);

        assertEquals(SIMPLE_CART, cartResult);
        verify(session, times(1)).getAttribute(CART_ATTRIBUTE);
    }

    @Test
    @DisplayName("Should get the cart info")
    public void getCartInfo() throws Exception {
        when(session.getAttribute(CART_ATTRIBUTE)).thenReturn(SIMPLE_CART);

        Cart cartResult = cartService.getCartInfo(1l, session);

        assertEquals(SIMPLE_CART, cartResult);
        verify(session, times(1)).getAttribute(CART_ATTRIBUTE);
    }

    @Test
    @DisplayName("Should delete cart")
    public void deleteCart() {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute(CART_ATTRIBUTE, SIMPLE_CART);

        assertNotNull(mockSession.getAttribute(CART_ATTRIBUTE));

        cartService.deleteCart(mockSession);

        assertNull(mockSession.getAttribute(CART_ATTRIBUTE));
    }
}