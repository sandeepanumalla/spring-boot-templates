package org.secured.securedwebsockets.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;

@org.springframework.stereotype.Service
public class Service {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public Service(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(String username, String message) {
        simpMessagingTemplate.convertAndSendToUser(username, "/topic", message);
    }



}
