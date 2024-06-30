package com.nagarro.shoppingcart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.shoppingcart.model.CartItem;
import com.nagarro.shoppingcart.model.Order;
import com.nagarro.shoppingcart.repo.CartRepository;
import com.nagarro.shoppingcart.repo.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Long userId ,Long msisdn) {
        CartItem cartItems = cartRepository.findByUserId(userId);
         

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(cartItems.getPrice());
        order.setOrderDate(LocalDateTime.now());
        order.setMsisdn(msisdn);
        order.setRateplan(cartItems.getRateplan());
        order.setRateplanID(cartItems.getRateplanID());
        Order savedOrder = orderRepository.save(order);

       
        // cartRepository.deleteByUserId(userId);

        return savedOrder;
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

