package com.nagarro.shoppingcart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.shoppingcart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}

