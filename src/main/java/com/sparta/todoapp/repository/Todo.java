package com.sparta.todoapp.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    private String title;
    private String content;
    private String userName;
    private String password;
    private LocalDateTime createAt;

    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createAt = LocalDateTime.now();
    }
}
