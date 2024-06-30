package com.nagarro.verification.config;

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

//	@Value("${rabbitmq.queue.activationToVerification.name}")
//    private String activationToVerificationQueueName;
    
    @Value("${rabbitmq.queue.verificationToActivation.name}")
    private String verificationToActivationQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.activation-service}")
    private String activationServiceRoutingKey;
    
   
    
    @Bean
    public Queue queue() {
        return new Queue(verificationToActivationQueueName, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingService2() {
        return BindingBuilder.bind(queue()).to(exchange()).with(activationServiceRoutingKey);
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

