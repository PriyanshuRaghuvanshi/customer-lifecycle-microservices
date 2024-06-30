package com.nagarro.activation.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.activation.exceptions.CustomException;
import com.nagarro.activation.models.Customer;
import com.nagarro.activation.service.ActivationService;


@Service
public class RabbitMQConsumer {
	
	@Autowired
	ActivationService activationService;
	

	 private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQConsumer.class);
	
    @RabbitListener(queues = "${rabbitmq.queue.verificationToActivation.name}")
    public void receiveMessageFromVerificationService(Customer customer) throws CustomException {
        // Process the received message 
    try {
         
    	LOGGER.info(String.format("Data received from Verification service -> %s", customer.toString()));
    	 List<String> roles= customer.getRoles();
    	
    	 
    	 activationService.updateActivationStatusInDatabase(customer);
    	
    	
    	if (roles.contains("CANNOT_ACCESS_NOTIFICATION")) {
            LOGGER.info("User not allowed to access notification.....");
        } else {
            // Role "CANNOT_ACCESS_NOTIFICATION" not found, proceed with pushNotificationAsync
            activationService.pushNotificationAsync(customer);
        }
    	
    } catch (CustomException ex) {
        // Handle the exception
        LOGGER.error("Error processing message: {}", ex.getMessage());

      
    }
}
    
}
    
    
    
    

