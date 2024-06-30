package com.nagarro.notification.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarro.notification.exceptions.CustomException;



@Service
public class RabbitMQConsumer {
	


	 private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQConsumer.class);
	
    @RabbitListener(queues = "${rabbitmq.queue.activationToNotification.name}")
    public void receiveMessageFromActivationService(String message) {
    	try {
            // Process the received message from Service 1
            LOGGER.info(String.format("Data received from Activation service -> %s", message));
         

        } catch (Exception e) {
            // Handle the exception and throw a custom exception
            throw new CustomException("Error processing message from Activation service", e);
        }
    }
}
