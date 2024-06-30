package com.nagarro.shoppingcart.controller;


import org.springframework.http.ResponseEntity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.shoppingcart.external.service.ContractService;
import com.nagarro.shoppingcart.model.CartItem;
import com.nagarro.shoppingcart.model.Order;
import com.nagarro.shoppingcart.service.CartService;
import com.nagarro.shoppingcart.service.OrderService;

@RestController
@RequestMapping("/cart/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;
   
    @Autowired
    private  ContractService contractService;

    
   
   
    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
        CartItem cartItem = cartService.addToCart(userId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }


    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId ,@RequestParam Long msisdn) {
        Order order = orderService.placeOrder(userId,msisdn);   
       // contractService.createContract(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrders(userId);
        return ResponseEntity.ok(orders);
    }
}

