package com.ccn.imageremove.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String IMAGE_EXCHANGE = "image.exchange";
    public static final String IMAGE_QUEUE = "image.queue";
    public static final String IMAGE_ROUTING_KEY = "image.remove";

    @Bean
    public DirectExchange imageExchange() {
        return new DirectExchange(IMAGE_EXCHANGE);
    }

    @Bean
    public Queue imageQueue() {
        return QueueBuilder.durable(IMAGE_QUEUE).build();
    }

    @Bean
    public Binding imageBinding() {
        return BindingBuilder
                .bind(imageQueue())
                .to(imageExchange())
                .with(IMAGE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter jsonMessageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}