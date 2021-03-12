package com.example.boardmanager.controller;

import com.example.boardmanager.dto.request.TaskRequestDto;
import com.example.boardmanager.dto.response.TaskResponseDto;
import com.example.boardmanager.mapper.impl.TaskMapper;
import com.example.boardmanager.model.Task;
import com.example.boardmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> save(@RequestBody TaskRequestDto taskRequestDto) {
        return new ResponseEntity<>(taskMapper.getDto(taskService
                .save(taskMapper.getEntity(taskRequestDto))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(
            @PathVariable Long id,
            @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskMapper.getEntity(taskRequestDto);
        task.setId(id);
        return new ResponseEntity<>(taskMapper.getDto(taskService.save(task)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(taskMapper.getDto(taskService.delete(id)), HttpStatus.OK);
    }
}
