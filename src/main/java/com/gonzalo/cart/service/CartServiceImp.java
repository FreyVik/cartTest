package com.gonzalo.cart.service;

import com.gonzalo.cart.exception.NotCartFoundException;
import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Constants;
import com.gonzalo.cart.model.Product;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gonzalo.cart.model.Constants.*;

@Service
public class CartServiceImp implements CartService {

    private Long cartId = 0l;

    Logger log = LoggerFactory.getLogger(CartServiceImp.class);

    @Value("${log.new.cart}")
    private String newCartLog;

    @Value("${log.cart.found}")
    private String cartFoundLog;

    @Value("${log.cart.not.found}")
    private String cartNotFoundLog;

    @Value("${log.cart.deleted}")
    private String cartDeletedLog;


    @Override
    public Cart addProducts(List<Product> products, HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE);

        if (cart == null) {
            log.info(newCartLog);
            cart = new Cart(this.cartId++);
        }

        cart.addProducts(products);

        updateSession(cart, session);

        return cart;
    }

    @Override
    public Cart getCartInfo(Long cartId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE);

        if (cart != null && cart.getId().equals(cartId)) {
            log.info(cartFoundLog, cartId);
        } else {
            log.error(cartNotFoundLog, cartId);
            throw new NotCartFoundException(cartId);
        }

        return cart;
    }

    @Override
    public void deleteCart(HttpSession session) {
        session.removeAttribute(CART_ATTRIBUTE);
        log.info(cartDeletedLog);
    }

    private void updateSession(Cart cart, HttpSession session) {
        session.setAttribute(CART_ATTRIBUTE, cart);
        session.setMaxInactiveInterval(SESSION_TIMEOUT);
    }
}
