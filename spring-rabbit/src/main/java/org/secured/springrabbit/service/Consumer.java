package org.secured.springrabbit.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @RabbitListener(queues = "secured-rabbit-queue", autoStartup = "true")
    public void consume(String message) {
        log.info("Received message: {}", message);
    }

}
