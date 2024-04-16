package org.secured.springmailrabbit.service;

import org.secured.springmailrabbit.request.EmailRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(EmailRequest emailRequest) {
        rabbitTemplate.convertAndSend("secured.mail.exchange", "secured.mail.routingKey", emailRequest);
    }

}
