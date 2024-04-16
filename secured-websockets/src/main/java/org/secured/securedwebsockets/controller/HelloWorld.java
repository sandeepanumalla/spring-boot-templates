package org.secured.securedwebsockets.controller;

import org.secured.securedwebsockets.request.MessageRequest;
import org.secured.securedwebsockets.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    private final Service service;

    public HelloWorld(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest) {
        String recipient = messageRequest.getUsername();
        String message = messageRequest.getMessage();

        if (recipient == null || recipient.isEmpty()) {
            return ResponseEntity.badRequest().body("Recipient username is required.");
        }

        service.sendMessage(recipient,  message);

        return ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully to user: " + recipient);
    }
}
