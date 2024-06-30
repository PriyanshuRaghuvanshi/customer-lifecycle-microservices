package com.nagarro.ordertocontract.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order {

  
    private Long id;

    private Long userId;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private Long msisdn;
    private String rateplan;
    private String rateplanID;
    // getters and setters
}

