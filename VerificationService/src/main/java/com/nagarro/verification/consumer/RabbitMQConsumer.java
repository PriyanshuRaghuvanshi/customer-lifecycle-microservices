package com.nagarro.verification.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.verification.exceptions.CustomException;
import com.nagarro.verification.models.Customer;
import com.nagarro.verification.service.CustomerVerificationService;



@Service
public class RabbitMQConsumer {
	
	@Autowired
	CustomerVerificationService customerVerificationService;


	 private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQConsumer.class);
	
    @RabbitListener(queues = "${rabbitmq.queue.activationToVerification.name}")
    public void receiveMessageFromActivationService( Customer customer ) throws CustomException {
     try {
    	
    	 List<String> roles= customer.getRoles();
    	 LOGGER.info("User Roles: " + roles);
    	 LOGGER.info(String.format("Data received from Activation service -> %s", customer.toString()));
    	
    	

        customerVerificationService.verifyCustomer(customer);
        
        
    } catch (Exception e) {
        // Handle the exception and throw a custom exception
        throw new CustomException("Error processing message from Activation service", e);
    }
    }
}
