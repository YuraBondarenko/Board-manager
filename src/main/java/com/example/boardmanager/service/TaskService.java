package com.example.boardmanager.service;

import com.example.boardmanager.model.Task;

public interface TaskService {
    Task save(Task task);

    Task delete(Long id);
}
