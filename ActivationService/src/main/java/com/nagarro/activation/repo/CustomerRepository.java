package com.nagarro.activation.repo;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.activation.models.ActivationStatus;
import com.nagarro.activation.models.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, String>{


	@Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.activationStatus = :activationStatus WHERE c.customerId = :customerId")
    void updateActivationStatus(String customerId, ActivationStatus activationStatus);

	

	Optional<Customer> findByMsisdn(Long msisdn);
    
}
