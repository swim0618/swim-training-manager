package com.example.swim_training_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "redirect:/top";
    }

    @GetMapping("/top")
    public String top() {
        return "top"; // templates/top.html を表示
    }
}