package com.nagarro.activation.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    

    @Value("${rabbitmq.queue.activationToVerification.name}")
    private String activationToVerificationQueueName;
    
    @Value("${rabbitmq.queue.verificationToActivation.name}")
    private String verificationToActivationQueueName;
    
    @Value("${rabbitmq.queue.activationToNotification.name}")
    private String activationToNotificationQueueName;
    
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    
    @Value("${rabbitmq.routingkey.activation-service}")
    private String activationServiceRoutingKey;//1
    
    @Value("${rabbitmq.routingkey.verification-service}")
    private String verificationServiceRoutingKey;//2

    @Value("${rabbitmq.routingkey.notification-service}")
    private String notificationServiceRoutingKey;//3

    

    @Bean
    public Queue Queue1() {
        return new Queue(activationToVerificationQueueName, true);
    }
    
    @Bean
    public Queue Queue2() {
        return new Queue(verificationToActivationQueueName, true);
    }
    
    @Bean
    public Queue Queue3() {
        return new Queue(activationToNotificationQueueName, true);
    }
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingService1() {
        return BindingBuilder.bind(Queue1()).to(exchange()).with(verificationServiceRoutingKey);
    }

    @Bean
    public Binding bindingService2() {
        return BindingBuilder.bind(Queue2()).to(exchange()).with(activationServiceRoutingKey);
    }
    
    @Bean
    public Binding bindingService3() {
        return BindingBuilder.bind(Queue3()).to(exchange()).with(notificationServiceRoutingKey);
    }
    
    @Bean
    public MessageConverter converter() {
    	return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    	RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
    	rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
    }
}

