package org.secured.springrabbit.controller;

import org.secured.springrabbit.service.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {


    private final Producer producer;

    public MyController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/message")
    public String getMessage() {
        producer.sendMessage("Hello World!");
        return "Hello World!";
    }
}
