package com.gonzalo.cart.service;

import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.models.CartDTO;
import com.gonzalo.cart.models.ProductDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public CartDTO addProducts(List<ProductDTO> products);

    public CartDTO getCartInfo(Long cartId);

    public void deleteCart();
}
