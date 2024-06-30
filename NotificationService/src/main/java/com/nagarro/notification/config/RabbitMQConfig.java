package com.nagarro.notification.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	
	@Value("${rabbitmq.queue.activationToNotification.name}")
	private String activationToNotificationQueueName;
	        
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.notification-service}")
    private String notificationServiceRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(activationToNotificationQueueName, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }


    @Bean
    public Binding bindingService3(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(notificationServiceRoutingKey);
    }
}

