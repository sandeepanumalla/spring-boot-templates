package org.secured.springrabbit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        log.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend("secured-rabbit-exchange","secured-rabbit-routing-key", message);
    }

}
