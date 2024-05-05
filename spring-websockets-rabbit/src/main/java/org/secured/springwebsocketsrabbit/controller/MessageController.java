package org.secured.springwebsocketsrabbit.controller;

import org.secured.springwebsocketsrabbit.requests.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;

@MessageMapping
public class MessageController {

    @MessageMapping("/message")
    public void receive(Notification notification) {

    }

}
