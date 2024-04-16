package org.secured.springmailrabbit.service;

import org.secured.springmailrabbit.request.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final EmailService emailService;

    public Consumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "secured.mail.queue", autoStartup = "true")
    public void consume(EmailRequest emailRequest) {

        log.info("Received message: {}", emailRequest);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailRequest.getFrom());
        simpleMailMessage.setTo(emailRequest.getTo());
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setText(emailRequest.getBody());
        log.info("Sending email: {}", simpleMailMessage);
        emailService.getJavaMailSender().send(simpleMailMessage);
        log.info("Sent email: {}", simpleMailMessage);
    }
}
