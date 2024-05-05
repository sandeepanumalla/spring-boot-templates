package org.secured.springwebsocketsrabbit.service;


import org.secured.springwebsocketsrabbit.requests.Notification;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public Producer(RabbitTemplate rabbitTemplate, SimpMessagingTemplate simpMessagingTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Async
    public void sendMessage(Notification notification) {
        MessageProperties properties = new MessageProperties();
//        properties.setHeader("x-death");
        rabbitTemplate.convertAndSend("secured-websockets-exchange", "routing-key", notification);
    }
}
