package org.secured.securedwebsockets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.secured.securedwebsockets.request.MessageRequest;
import org.secured.securedwebsockets.service.Service;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final Service service;

    @MessageMapping("/chat")
    public void chat(@Payload MessageRequest messageRequest) {
        log.info("Message received: {}", messageRequest.getMessage());
        service.sendMessage(messageRequest.getUsername(), messageRequest.getMessage());
    }

}
