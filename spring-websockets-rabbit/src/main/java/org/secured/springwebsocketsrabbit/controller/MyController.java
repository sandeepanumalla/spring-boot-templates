package org.secured.springwebsocketsrabbit.controller;

import org.secured.springwebsocketsrabbit.requests.Notification;
import org.secured.springwebsocketsrabbit.requests.UserRegistrationRequest;
import org.secured.springwebsocketsrabbit.service.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private final Producer producer;

    public MyController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/send")
    public ResponseEntity<?> send(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        Notification notification = Notification.builder()
                .recipient(userRegistrationRequest.getName())
                .message("hey there!")
                .type("basic")
                .build();
        producer.sendMessage(notification);
        return ResponseEntity.ok().body(notification);
    }

}
