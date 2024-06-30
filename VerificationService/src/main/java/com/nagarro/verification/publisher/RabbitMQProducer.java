package com.nagarro.verification.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarro.verification.exceptions.CustomException;
import com.nagarro.verification.models.ActivationStatus;
import com.nagarro.verification.models.Customer;

@Service
public class RabbitMQProducer {

	 @Value("${rabbitmq.exchange.name}")
	 private String exchange;
	
    @Value("${rabbitmq.routingkey.activation-service}")
    private String activationServiceRoutingKey;
    
    private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQProducer.class);
    
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}


    public void sendMessageToActivationService(Customer verified_customer, ActivationStatus activationStatus) throws CustomException {
        // Use RabbitTemplate to send the user data to Service 1
    	try {
    		
    	verified_customer.setActivationStatus(activationStatus);
        rabbitTemplate.convertAndSend(exchange, activationServiceRoutingKey, verified_customer);
        LOGGER.info(String.format("Data sent to activation service -> %s", verified_customer.toString()));
    	} catch (Exception e) {
            // Handle the exception and throw a custom exception
            throw new CustomException("Error sending message to Activation service", e);
        }
    	}
}
