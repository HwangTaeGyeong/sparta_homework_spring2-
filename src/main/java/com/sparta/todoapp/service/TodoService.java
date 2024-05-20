package com.sparta.todoapp.service;

import com.sparta.todoapp.controller.TodoRequestDto;
import com.sparta.todoapp.repository.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //할일 생성
    public Todo createTodo(TodoRequestDto dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }
}
