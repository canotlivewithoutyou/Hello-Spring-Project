package com.example.hello_spring.repo;

import com.example.hello_spring.dto.TodoDtos.TodoResp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTodoRepository {
    private final Map<Long, TodoResp> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public TodoResp save(String title) {
        Long id = seq.incrementAndGet();
        TodoResp todo = new TodoResp(id, title, false);
        store.put(id, todo);
        return todo;
    }

    public List<TodoResp> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(TodoResp::id))
                .toList();
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
