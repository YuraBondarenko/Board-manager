package com.example.boardmanager.mapper.impl;

import com.example.boardmanager.dto.response.BoardColumnTaskResponseDto;
import com.example.boardmanager.mapper.MapperToDto;
import com.example.boardmanager.model.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BoardColumnTaskMapper implements MapperToDto<BoardColumnTaskResponseDto, List<Task>> {
    private static final int ANY = 0;

    @Override
    public BoardColumnTaskResponseDto getDto(List<Task> entity) {
        BoardColumnTaskResponseDto dto = new BoardColumnTaskResponseDto();
        dto.setBoardId(entity.get(ANY).getColumn().getBoard().getId());
        dto.setBoardName(entity.get(ANY).getColumn().getBoard().getName());

        List<Long> columnIds = entity.stream().map(s -> s.getColumn()
                .getId()).distinct().collect(Collectors.toList());
        Map<Long, List<Long>> ids = new HashMap<>();
        for (int i = 0; i < columnIds.size(); i++) {
            int finalI = i;
            List<Long> taskIds = entity.stream()
                    .filter(s -> s.getColumn().getId().equals(columnIds.get(finalI)))
                    .map(Task::getId).collect(Collectors.toList());
            ids.put(columnIds.get(i), taskIds);
        }
        dto.setColumnIdAndTaskIds(ids);

        return dto;
    }
}
