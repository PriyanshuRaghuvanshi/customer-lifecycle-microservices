package com.nagarro.verification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.verification.exceptions.CustomException;
import com.nagarro.verification.models.ActivationStatus;
import com.nagarro.verification.models.Customer;
import com.nagarro.verification.publisher.RabbitMQProducer;

@Service
public class CustomerVerificationService {

	@Autowired
	RabbitMQProducer producer;
	
	@Autowired
    private CustomerService customerService;
	
	public void verifyCustomer( Customer customer) throws CustomException {
	try {	
		
		
		
		String ID =customer.getCustomerId();
		ActivationStatus activationStatus;
	     
		if(customerService.findById(ID)) {
	    	 customerService.updateActivationStatus(ID, ActivationStatus.SUCCESSFUL);
	    	 activationStatus=ActivationStatus.SUCCESSFUL;
	     }else {
	    	 customer.setActivationStatus(ActivationStatus.SUCCESSFUL);
	    	 activationStatus=ActivationStatus.SUCCESSFUL;
	    	 customerService.saveUser(customer);
	     }
		
	     producer.sendMessageToActivationService(customer,activationStatus);
	} catch (Exception e) {
        // Handle the exception and throw a custom exception
        throw new CustomException("customer verification failed ", e);
    }
	}
	
}
