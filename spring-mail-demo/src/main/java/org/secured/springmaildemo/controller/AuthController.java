package org.secured.springmaildemo.controller;

import org.secured.springmaildemo.dto.EmailDetails;
import org.secured.springmaildemo.dto.RequestDto;
import org.secured.springmaildemo.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmailService emailService;

    public AuthController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/details")
    public ResponseEntity<RequestDto> getDetails(@RequestBody RequestDto requestDto) {

        emailService.sendEmail(
                        EmailDetails.builder()
                        .to(requestDto.getEmail())
                        .body("thanks for registering with us, " + requestDto.getName())
                        .subject("Thanks for registering with us")
                .build());
        return ResponseEntity.ok(requestDto);
    }
}
