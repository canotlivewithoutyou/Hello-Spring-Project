package com.example.hello_spring.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@Validated
public class EchoController{

    @GetMapping("/echo")
    public Map<String, String> echo(@RequestParam @NotBlank String msg){
        return Map.of("echo", msg);
    }
}