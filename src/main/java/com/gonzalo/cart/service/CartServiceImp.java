package com.gonzalo.cart.service;

import com.gonzalo.cart.exception.NotCartFoundException;
import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Constants;
import com.gonzalo.cart.model.Product;
import com.gonzalo.cart.models.CartDTO;
import com.gonzalo.cart.models.ProductDTO;
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

    private final HttpSession session;

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

    public CartServiceImp(HttpSession session) {
        this.session = session;
    }

    @Override
    public CartDTO addProducts(List<ProductDTO> products) {
        CartDTO cartDto = (CartDTO) session.getAttribute(CART_ATTRIBUTE);
        Cart cart = null;
        if(cartDto != null) {
            cart = Cart.builder()
                    .id(cartDto.getId())
                    .products(cartDto.getProducts())
                    .totalPrice(cartDto.getTotalPrice())
                    .totalProducts(cartDto.getTotalProducts())
                    .build();
        }

        if (cart == null) {
            log.info(newCartLog);
            cart = new Cart(this.cartId++);
        }

        cart.addProducts(products);

        updateSession(cart);

        return generateNewCartDTO(cart);
    }

    @Override
    public CartDTO getCartInfo(Long cartId) {
        CartDTO cartDto = (CartDTO) session.getAttribute(CART_ATTRIBUTE);
        Cart cart = null;
        if(cartDto != null) {
            cart = Cart.builder()
                    .id(cartDto.getId())
                    .products(cartDto.getProducts())
                    .totalPrice(cartDto.getTotalPrice())
                    .totalProducts(cartDto.getTotalProducts())
                    .build();
        }

        if (cart != null && cart.getId().equals(cartId)) {
            log.info(cartFoundLog, cartId);
        } else {
            log.error(cartNotFoundLog, cartId);
            throw new NotCartFoundException(cartId);
        }

        return generateNewCartDTO(cart);
    }

    @Override
    public void deleteCart() {
        session.removeAttribute(CART_ATTRIBUTE);
        log.info(cartDeletedLog);
    }

    private void updateSession(Cart cart) {
        session.setAttribute(CART_ATTRIBUTE, generateNewCartDTO(cart));
        session.setMaxInactiveInterval(SESSION_TIMEOUT);
    }

    private CartDTO generateNewCartDTO(Cart cart) {
        CartDTO newCartDTO = new CartDTO();

        newCartDTO.setId(cart.getId());
        newCartDTO.setProducts(cart.getProducts());
        newCartDTO.setTotalPrice(cart.getTotalPrice());
        newCartDTO.setTotalProducts(cart.getTotalProducts());

        return newCartDTO;
    }
}
