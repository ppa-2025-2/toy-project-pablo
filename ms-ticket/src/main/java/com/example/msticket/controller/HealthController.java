package com.example.msticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Ms-Ticket Service is healthy!";
    }

    @GetMapping("/")
    public String home() {
        return "Ms-Ticket Service is running on port 8081";
    }
}