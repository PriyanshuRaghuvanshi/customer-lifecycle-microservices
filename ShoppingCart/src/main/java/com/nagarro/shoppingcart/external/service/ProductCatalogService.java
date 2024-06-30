package com.nagarro.shoppingcart.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.shoppingcart.model.Product;

@FeignClient(name = "product-catalog-service", url = "http://localhost:8090")
public interface ProductCatalogService {

    @GetMapping("/products/v1/{id}")
    Product getProductById(@PathVariable Long id);
}

