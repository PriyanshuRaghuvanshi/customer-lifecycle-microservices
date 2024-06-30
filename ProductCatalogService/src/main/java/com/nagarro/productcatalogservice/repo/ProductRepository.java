package com.nagarro.productcatalogservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.productcatalogservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
