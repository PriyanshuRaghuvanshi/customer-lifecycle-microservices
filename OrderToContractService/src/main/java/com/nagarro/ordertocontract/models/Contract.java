package com.nagarro.ordertocontract.models;

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
@Table(name="contract_details")
public class Contract{

    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Contractid;
    private String rateplanId;
    private String rateplan;
    private Long msisdn;

    
    
    

    
}
