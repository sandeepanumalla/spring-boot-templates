package com.example.securityinmemory.controller;

import com.example.securityinmemory.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Products {

    @GetMapping("/products")
    public String getProducts() {
        return "3 products";
    }


    @GetMapping("/login")
    public String login(@RequestBody User user) {
        return user.getUsername() + "welcome back";
    }

}
