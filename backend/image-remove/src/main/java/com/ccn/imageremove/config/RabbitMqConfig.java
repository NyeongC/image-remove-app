package com.ccn.imageremove.config;

import org.springframework.amqp.core.*;
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
}