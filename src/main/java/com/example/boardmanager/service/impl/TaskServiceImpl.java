package com.example.boardmanager.service.impl;

import com.example.boardmanager.model.Task;
import com.example.boardmanager.repository.TaskRepository;
import com.example.boardmanager.service.TaskService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task delete(Long id) {
        Task task = taskRepository.getOne(id);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<Task> getWithRelations(Long id) {
        return taskRepository.getWithRelations(id);
    }

    @Override
    public void deleteByBoardId(Long id) {
        taskRepository.deleteByColumnBoardId(id);
    }

    @Override
    public void deleteByColumnId(Long id) {
        taskRepository.deleteByColumnId(id);
    }
}
