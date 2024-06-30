package com.nagarro.shoppingcart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.shoppingcart.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long>{

	 CartItem findByUserId(Long userId);
	 
}
