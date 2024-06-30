package com.nagarro.activation.publisher;


import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nagarro.activation.exceptions.CustomException;
import com.nagarro.activation.models.Customer;

@Service
public class RabbitMQProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
    @Value("${rabbitmq.routingkey.verification-service}")
    private String verificationServiceRoutingKey;

    @Value("${rabbitmq.routingkey.notification-service}")
    private String notificationServiceRoutingKey;
    
     private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQProducer.class);
    
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

    
    public void sendMessageToVerificationService( Customer customer)throws CustomException {
        // Use RabbitTemplate to send the user data to VerificationService
    		
    	
    	try {
           
    		
    		//Extracting roles    
//    		     List<String> roles = extractRolesFromAuthentication();
//                 LOGGER.info("User Roles: " + roles);
//                 customer.setRoles(roles);

             rabbitTemplate.convertAndSend(exchange, verificationServiceRoutingKey, customer);
             LOGGER.info(String.format("Data sent to verification service -> %s", customer.toString()));
    	 }catch (Exception e) {
             // Apply exception handling with custom exceptions
             throw new CustomException("Error sending data to verification service", e);
         }
    	 }

    

	
    public void sendMessageToNotificationService(String msg) throws CustomException {
        // Use RabbitTemplate to send the user data to NotificationService
             
        try {
             rabbitTemplate.convertAndSend(exchange, notificationServiceRoutingKey, msg);
               LOGGER.info(String.format("Data sent to notification service -> %s", msg));
        	 }catch (Exception e) {
        		 throw new CustomException("Error sending data to notification service", e);
    }  
   }    
        
        
 //   private  List<String> extractRolesFromAuthentication( ) {
    	 // Obtain Authentication object from SecurityContextHolder
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//    	List<String> roles = new ArrayList<>();
//             if (authentication != null && authentication.getAuthorities() != null) {
//                 roles = authentication.getAuthorities()
//                         .stream()
//                         .map(GrantedAuthority::getAuthority)
//                         .collect(Collectors.toList());
//             }
//             return roles;
//	}
     
   
   
  
   
}
