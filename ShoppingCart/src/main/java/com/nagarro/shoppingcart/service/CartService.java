package com.nagarro.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.shoppingcart.external.service.ProductCatalogService;
import com.nagarro.shoppingcart.model.CartItem;
import com.nagarro.shoppingcart.model.Product;
import com.nagarro.shoppingcart.repo.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductCatalogService productCatalogFeignClient;

    public CartItem addToCart(Long userId, Long productId) {
        Product product = productCatalogFeignClient.getProductById(productId);
        System.out.print(product + "yes working");
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setRateplan(product.getRateplan());
            cartItem.setPrice(product.getPrice());
            return cartRepository.save(cartItem);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

   
}
