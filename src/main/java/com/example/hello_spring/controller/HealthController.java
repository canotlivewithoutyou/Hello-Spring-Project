package com.example.hello_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

record HealthResp(String status) {}

@RestController //HTTP요청을 받는곳
public class HealthController {
    @GetMapping("/health") //GET/health 들어오면 이 메서드 실행
    public HealthResp health() {
        return new HealthResp("ok");
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("pong", "ok");
    }

}

