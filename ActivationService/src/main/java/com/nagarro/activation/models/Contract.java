package com.nagarro.activation.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Contract{

    
    private Long contractid;
    private String rateplanId;
    private String rateplan;
    private Long msisdn;

   
   
}
