package org.secured.springmailasync.controller;

import org.secured.springmailasync.request.EmailRequest;
import org.secured.springmailasync.request.UserRegistrationRequest;
import org.secured.springmailasync.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class MyController {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final EmailService emailService;

    public MyController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody UserRegistrationRequest registrationRequest) {

        emailService.sendEmail(EmailRequest.builder()
                        .to(registrationRequest.getEmail())
                        .from(fromEmail)
                        .body("Thanks for registering with us, " + registrationRequest.getName())
                        .subject("Welcome to our site")
                .build());

        return ResponseEntity.accepted().body(registrationRequest);
    }
}
