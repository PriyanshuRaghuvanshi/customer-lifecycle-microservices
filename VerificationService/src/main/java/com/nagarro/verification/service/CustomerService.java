package com.nagarro.verification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.verification.models.ActivationStatus;
import com.nagarro.verification.models.Customer;
import com.nagarro.verification.repo.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
    private CustomerRepository customerRepository;

    public void saveUser(Customer customer) {
    	customerRepository.save(customer);
    }

	public Boolean findById(String ID) {
		// TODO Auto-generated method stub
		return customerRepository.existsById(ID);
	}

	public void updateActivationStatus(String customerId, ActivationStatus activationStatus) {
        // Update activationStatus for the given customerId
        customerRepository.updateActivationStatus(customerId, activationStatus);
    }
  
	
}
