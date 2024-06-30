package com.nagarro.activation.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nagarro.activation.exceptions.CustomException;
import com.nagarro.activation.models.Customer;
import com.nagarro.activation.publisher.RabbitMQProducer;



@Service
public class ActivationService {

	@Autowired
	RabbitMQProducer producer;
	
	@Autowired
    private CustomerService customerService ;
	
	 private static final Logger logger = LoggerFactory.getLogger(ActivationService.class);
	



    @Async
    public CompletableFuture<Void> updateActivationStatusInDatabase(Customer customer) throws CustomException {
              	
    	 try {
    		// logger.info(Thread.currentThread().getName());
    		 customerService.updateActivationStatus(customer.getCustomerId(), customer.getActivationStatus());
    		 return CompletableFuture.completedFuture(null);    
    	 }catch (Exception e) {
    	     throw new CustomException("Error updating database: " , e);
    	     }
	
    }

   @Async
    public CompletableFuture<Void>  pushNotificationAsync(Customer customer)throws CustomException {
    	try {
    		String message = "CustomerId-->"+ customer.getCustomerId().toString()+" ActivationStatus ---->"+ customer.getActivationStatus();
    		//logger.info(Thread.currentThread().getName());
    		producer.sendMessageToNotificationService(message);
    		return CompletableFuture.completedFuture(null);
    		
    	} catch (Exception e) {
    		throw new CustomException("Error pushing notification to: " + customer.getCustomerId() + customer.getFirstName(), e);
    	}
		
	}
    
}