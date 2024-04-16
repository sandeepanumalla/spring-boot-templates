package com.example.securityinmemory.controller;

import com.example.securityinmemory.model.User;
import com.example.securityinmemory.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {


    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/products";
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {

    }


    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "some value");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*60);
        response.addCookie(cookie);
        return "redirect:/products";
    }
}