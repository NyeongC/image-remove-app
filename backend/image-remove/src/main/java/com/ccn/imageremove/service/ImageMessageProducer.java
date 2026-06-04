package com.ccn.imageremove.service;

import com.ccn.imageremove.config.RabbitMqConfig;
import com.ccn.imageremove.dto.ImageJobMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(ImageJobMessage message) {
        log.info("RabbitMQ 이미지 작업 메시지 발행. originalKey={}, originalUrl={}",
                message.originalKey(),
                message.originalUrl());

        rabbitTemplate.convertAndSend(
                RabbitMqConfig.IMAGE_EXCHANGE,
                RabbitMqConfig.IMAGE_ROUTING_KEY,
                message
        );
    }
}