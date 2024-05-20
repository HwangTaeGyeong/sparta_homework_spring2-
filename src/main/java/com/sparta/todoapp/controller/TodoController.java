package com.sparta.todoapp.controller;

import com.sparta.todoapp.CommonResponse;
import com.sparta.todoapp.repository.Todo;
import com.sparta.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CommonResponse<TodoResponseDto>> postTodo(@RequestBody TodoRequestDto dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(response)
                .build());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.")
                .data(responseDto)
                .build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TodoResponseDto>>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDto> responseDtos = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(CommonResponse.<List<TodoResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.")
                .data(responseDtos)
                .build());
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("수정이 완료되었습니다.")
                .data(responseDto)
                .build());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료되었습니다.")
                .build());
    }
}
