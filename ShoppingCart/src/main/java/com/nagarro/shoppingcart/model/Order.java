package com.nagarro.shoppingcart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;

    private Long userId;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private Long msisdn  ;
    private String rateplan;
    private String rateplanID;
    // getters and setters
}

