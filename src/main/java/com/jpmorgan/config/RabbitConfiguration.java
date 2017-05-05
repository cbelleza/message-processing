package com.jpmorgan.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure Rabbit messaging service
 * 
 * @author Carlos Alberto
 *
 */
@EnableRabbit
@Configuration
public class RabbitConfiguration {

    /**
     * Define default queue (sale)
     * 
     * @param queueName
     * @return Queue
     */
    @Bean
    public Queue queue(@Value("${queue.name}") String queueName) {
        return new Queue(queueName);
    }

    /**
     * Configure RabbitTemplate to send messages in JSON format
     * 
     * @param connectionFactory
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
