package com.example.hello_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

record HealthResp(String status) {}

@RestController
public class HealthController {
    @GetMapping("/health")
    public HealthResp health() {
        return new HealthResp("ok");
    }
}
