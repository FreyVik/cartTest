package com.gonzalo.cart.controller;

import com.gonzalo.cart.CartCleanupSchedule;
import com.gonzalo.cart.model.Cart;
import com.gonzalo.cart.model.Product;
import com.gonzalo.cart.model.ProductListDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CartController {

    Logger log = LoggerFactory.getLogger(CartController.class);

    private final CartCleanupSchedule cartCleanupSchedule;

    public CartController(CartCleanupSchedule cartCleanupSchedule) {
        this.cartCleanupSchedule = cartCleanupSchedule;
    }

    @PostMapping(value = "addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addProduct(@RequestBody @Valid ProductListDTO products, HttpSession session) {

        Cart cart = (Cart) this.cartCleanupSchedule.getSessionAttribute("cart");

        if (cart == null) {
            cart = new Cart((this.cartCleanupSchedule.getSessionAttribute("lastCardId") != null ? Long.parseLong(this.cartCleanupSchedule.getSessionAttribute("lastCardId").toString()) : 0l));
        }

        cart.addProducts(products.getProducts());

        this.cartCleanupSchedule.addSessionAttribute("cart", cart);
        this.cartCleanupSchedule.addSessionAttribute("lastCardId", cart.getId());
        this.cartCleanupSchedule.addSessionAttribute("lastCardUpdate", new Date());
        this.cartCleanupSchedule.cleanupCart();

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long cartId) {
        Cart cart = (Cart) this.cartCleanupSchedule.getSessionAttribute("cart");

        if (cart != null && cart.getId().equals(cartId)) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> deleteCart(HttpSession session) {
        session.removeAttribute("cart");

        return new ResponseEntity<>("Cart deleted", HttpStatus.OK);
    }

    @PostMapping(value = "addProductError", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addProductError(@Valid @RequestBody Product products) {

        Cart cart = (Cart) this.cartCleanupSchedule.getSessionAttribute("cart");

        if (cart == null) {
            cart = new Cart((this.cartCleanupSchedule.getSessionAttribute("lastCardId") != null ? Long.parseLong(this.cartCleanupSchedule.getSessionAttribute("lastCardId").toString()) : 0l));
        }

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
