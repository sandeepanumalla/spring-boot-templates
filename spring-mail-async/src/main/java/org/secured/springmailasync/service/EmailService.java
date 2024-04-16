package org.secured.springmailasync.service;

import lombok.extern.slf4j.Slf4j;
import org.secured.springmailasync.request.EmailRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;


    }


    @Async
    public void sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailRequest.getFrom());
            simpleMailMessage.setTo(emailRequest.getTo());
            simpleMailMessage.setSubject(emailRequest.getSubject());
            simpleMailMessage.setText(emailRequest.getBody());
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.info("Failure occurred while sending email", e);
        }
    }


}
