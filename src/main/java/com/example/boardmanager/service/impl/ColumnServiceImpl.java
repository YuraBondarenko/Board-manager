package com.example.boardmanager.service.impl;

import com.example.boardmanager.model.Column;
import com.example.boardmanager.repository.ColumnRepository;
import com.example.boardmanager.service.ColumnService;
import com.example.boardmanager.service.TaskService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;
    private final TaskService taskService;

    public ColumnServiceImpl(ColumnRepository columnRepository, TaskService taskService) {
        this.columnRepository = columnRepository;
        this.taskService = taskService;
    }

    @Override
    public Column save(Column column) {
        return columnRepository.save(column);
    }

    @Override
    @Transactional
    public Column delete(Long id) {
        Column column = columnRepository.getOne(id);
        taskService.deleteByColumnId(id);
        columnRepository.delete(column);
        return column;
    }

    @Override
    public void deleteByBoardId(Long id) {
        columnRepository.deleteByBoardId(id);
    }

    @Override
    public Column getById(Long id) {
        return Optional.of(columnRepository.getOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Cannot find column with id " + id));
    }
}
