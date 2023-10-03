package com.gonzalo.cart.controller;

import com.gonzalo.cart.exception.NotCartFoundException;
import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Product;
import com.gonzalo.cart.model.dto.ProductListDTO;
import com.gonzalo.cart.service.CartService;
import com.gonzalo.cart.service.CartServiceImp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CartController {

    private final CartService cartService;

    @Value("${log.cart.deleted}")
    private String deleteCartLog;

    @Value("${log.products.add}")
    private String productsToAddLog;

    @Value("${log.cart.info}")
    private String cartInfoFromIdLog;

    @Value("${log.cart.deleting}")
    private String deletingCartLog;

    public CartController(CartServiceImp cartService) {
        this.cartService = cartService;
    }

    Logger log = LoggerFactory.getLogger(CartController.class);

    @PostMapping(value = "addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addProduct(@RequestBody @Valid ProductListDTO products, HttpSession session) {
        log.info(productsToAddLog, products);

        Cart cart = cartService.addProducts(products.getProducts(), session);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long cartId, HttpSession session) throws NotCartFoundException {
        log.info(cartInfoFromIdLog, cartId);

        Cart cart = cartService.getCartInfo(cartId, session);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> deleteCart(HttpSession session) {
        log.info(deletingCartLog);

        cartService.deleteCart(session);

        return new ResponseEntity<>(deleteCartLog, HttpStatus.OK);
    }
}
