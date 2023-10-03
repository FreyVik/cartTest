package com.gonzalo.cart.service;

import com.gonzalo.cart.exception.NotCartFoundException;
import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public Cart addProducts(List<Product> products, HttpSession session);

    public Cart getCartInfo(Long cartId, HttpSession session);

    public void deleteCart(HttpSession session);
}
