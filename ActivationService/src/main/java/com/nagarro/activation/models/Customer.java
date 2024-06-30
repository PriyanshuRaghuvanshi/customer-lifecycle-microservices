package com.nagarro.activation.models;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	
	@Id
	@NotNull
    private String customerId;

    private String customerCode;
		
    private boolean emailVerificationStatus;	
    private String firstName;
    private String lastName;
  
    private Long msisdn;

    private String preferredLanguage;
    
    private boolean paymentResponsible;

    private boolean emailSent;

    @Embedded
    private Geolocation geolocation;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus ;
    
    @ElementCollection
    private List<Contract> contracts;
   
    @Transient
    private List<String> roles;

	

   
}
