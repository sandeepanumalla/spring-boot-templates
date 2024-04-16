package org.secured.springmailrabbit.controller;

import org.secured.springmailrabbit.request.EmailRequest;
import org.secured.springmailrabbit.request.UserRegistrationRequest;
import org.secured.springmailrabbit.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String email;

    public AuthController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        emailService.sendEmail(EmailRequest.builder()
                        .to(userRegistrationRequest.getEmail())
                        .from(email)
                        .body("Thanks for registering! " + userRegistrationRequest.getName())
                        .subject("Thanks for registering!")
                .build());
        return ResponseEntity.ok().body("Email has been sent.");
    }
}
