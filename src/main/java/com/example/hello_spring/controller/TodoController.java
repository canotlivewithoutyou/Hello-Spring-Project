package com.example.hello_spring.controller;

import com.example.hello_spring.dto.TodoDtos.CreateTodoReq;
import com.example.hello_spring.dto.TodoDtos.TodoResp;
import com.example.hello_spring.repo.InMemoryTodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    // 간단히 직접 생성 (DI 없이) — 나중에 @Service/@Repository로 분리 가능
    private final InMemoryTodoRepository repo = new InMemoryTodoRepository();

    // 생성
    @PostMapping
    public ResponseEntity<TodoResp> create(@RequestBody @Valid CreateTodoReq req) {
        TodoResp saved = repo.save(req.title());
        return ResponseEntity.ok(saved);
    }

    // 전체 조회
    @GetMapping
    public List<TodoResp> list() {
        return repo.findAll();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = repo.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
