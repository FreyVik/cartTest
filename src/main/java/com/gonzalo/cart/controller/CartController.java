package com.gonzalo.cart.controller;

import com.gonzalo.cart.api.CartApi;
import com.gonzalo.cart.models.CartDTO;
import com.gonzalo.cart.models.ProductListDTO;
import com.gonzalo.cart.service.CartService;
import com.gonzalo.cart.service.CartServiceImp;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CartController implements CartApi {

    private final CartService cartService;

    private final HttpSession session;

    @Value("${log.cart.deleted}")
    private String deleteCartLog;

    @Value("${log.products.add}")
    private String productsToAddLog;

    @Value("${log.cart.info}")
    private String cartInfoFromIdLog;

    @Value("${log.cart.deleting}")
    private String deletingCartLog;

    public CartController(CartServiceImp cartService, HttpSession session) {
        this.cartService = cartService;
        this.session = session;
    }

    Logger log = LoggerFactory.getLogger(CartController.class);

    @Override
    public ResponseEntity<CartDTO> addProduct(ProductListDTO products) {
        log.info(productsToAddLog, products);

        CartDTO cart = cartService.addProducts(products.getProducts());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDTO> getCart(Long cartId) {
        log.info(cartInfoFromIdLog, cartId);

        CartDTO cart = cartService.getCartInfo(cartId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCart() {
        log.info(deletingCartLog);

        cartService.deleteCart();

        return new ResponseEntity<>(deleteCartLog, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<CartDTO> addProduct(@RequestBody @Valid ProductListDTO products, HttpSession session) {
//        log.info(productsToAddLog, products);
//
//        CartDTO cart = cartService.addProducts(products.getProducts(), session);
//
//        return new ResponseEntity<>(cart, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<CartDTO> getCart(@PathVariable("id") Long cartId, HttpSession session) throws NotCartFoundException {
//        log.info(cartInfoFromIdLog, cartId);
//
//        CartDTO cart = cartService.getCartInfo(cartId, session);
//
//        return new ResponseEntity<>(cart, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "delete")
//    public ResponseEntity<?> deleteCart(HttpSession session) {
//        log.info(deletingCartLog);
//
//        cartService.deleteCart(session);
//
//        return new ResponseEntity<>(deleteCartLog, HttpStatus.OK);
//    }
}
