package com.nagarro.verification.repo;

import com.nagarro.verification.models.ActivationStatus;
import com.nagarro.verification.models.Customer;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	boolean existsById(String uUID);

	
	@Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.activationStatus = :activationStatus WHERE c.customerId = :customerId")
    void updateActivationStatus(String customerId, ActivationStatus activationStatus);
}
