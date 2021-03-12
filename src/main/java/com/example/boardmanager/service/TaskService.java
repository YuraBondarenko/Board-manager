package com.example.boardmanager.service;

import com.example.boardmanager.model.Task;
import java.util.List;

public interface TaskService {
    Task save(Task task);

    Task delete(Long id);

    List<Task> getWithRelations(Long id);

    void deleteByBoardId(Long id);

    void deleteByColumnId(Long id);
}
