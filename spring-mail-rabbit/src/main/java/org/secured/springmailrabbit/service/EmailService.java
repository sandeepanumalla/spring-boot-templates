package org.secured.springmailrabbit.service;

import org.secured.springmailrabbit.request.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    private final Producer producer;

    public EmailService(JavaMailSender javaMailSender, Producer producer) {
        this.javaMailSender = javaMailSender;
        this.producer = producer;
    }

    @Async
    public void sendEmail(EmailRequest emailRequest) {
        try {
            log.info("Sending email from producer to {}", emailRequest.getTo());
            producer.send(emailRequest);
        } catch (MailException e) {
            log.info("Error sending email: {}", e.getMessage());
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }
}
