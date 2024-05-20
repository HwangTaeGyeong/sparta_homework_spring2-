package com.sparta.todoapp.controller;

import com.sparta.todoapp.repository.Todo;
import com.sparta.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1.0/todo")
public class TodoController {

    public final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDto> responseDtos = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDtos);
    }
}
