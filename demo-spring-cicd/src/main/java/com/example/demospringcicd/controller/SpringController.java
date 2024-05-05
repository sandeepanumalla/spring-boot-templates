package com.example.demospringcicd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/greetings")
public class SpringController {

    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String greeting() {
        return "Greetings";
    }

    @RequestMapping(value = "/result", method = {RequestMethod.GET, RequestMethod.POST})
    public String result(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "Result";
    }

}
