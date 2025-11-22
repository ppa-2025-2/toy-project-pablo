package com.example.msuser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Ms-User Service is healthy!";
    }

    @GetMapping("/")
    public String home() {
        return "Ms-User Service is running on port 8082";
    }
}