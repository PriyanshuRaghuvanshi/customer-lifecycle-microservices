package com.nagarro.shoppingcart.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartid;
    private Long productId;
    private String rateplan;
    private String rateplanID;
    private BigDecimal price;
    private Long userId;

    // getters and setters
}

