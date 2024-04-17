package org.secured.springrabbitwebsockets.controller;

import org.secured.springrabbitwebsockets.dto.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MyController {

    @RequestMapping("/sendNotification")
    public ResponseEntity<?> sendNotification(Notification notification) {

        return null;
    }

}
