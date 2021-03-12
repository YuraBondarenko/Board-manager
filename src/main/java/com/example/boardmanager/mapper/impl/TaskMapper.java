package com.example.boardmanager.mapper.impl;

import com.example.boardmanager.dto.request.TaskRequestDto;
import com.example.boardmanager.dto.response.TaskResponseDto;
import com.example.boardmanager.mapper.MapperToDto;
import com.example.boardmanager.mapper.MapperToEntity;
import com.example.boardmanager.model.Task;
import com.example.boardmanager.service.ColumnService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements MapperToDto<TaskResponseDto, Task>,
        MapperToEntity<Task, TaskRequestDto> {
    private final ColumnService columnService;

    public TaskMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public TaskResponseDto getDto(Task entity) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(entity.getId());
        taskResponseDto.setTitle(entity.getTitle());
        taskResponseDto.setDescription(entity.getDescription());
        taskResponseDto.setColumnId(entity.getColumn().getId());
        return taskResponseDto;
    }

    @Override
    public Task getEntity(TaskRequestDto dto) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setTitle(dto.getTitle());
        if (dto.getColumnId() != null) {
            task.setColumn(columnService.getById(dto.getColumnId()));
        }
        return task;
    }
}
