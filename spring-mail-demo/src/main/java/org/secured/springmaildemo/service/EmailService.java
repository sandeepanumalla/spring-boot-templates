package org.secured.springmaildemo.service;

import lombok.extern.slf4j.Slf4j;
import org.secured.springmaildemo.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendEmail(EmailDetails emailDetails) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailSender);
            message.setTo(emailDetails.getTo());
            message.setText(emailDetails.getBody());
            message.setSubject(emailDetails.getSubject());
            javaMailSender.send(message);
        } catch (MailException e) {
            log.info("Failure occurred while sending email", e);
        }
    }

}
