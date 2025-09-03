package com.example.hello_spring.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoDtos {
    // 생성 요청
    public record CreateTodoReq(@NotBlank String title) {}

    // 응답용
    public record TodoResp(Long id, String title, boolean done) {}
}
