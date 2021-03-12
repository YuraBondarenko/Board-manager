package com.example.boardmanager.dto.response;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class BoardColumnTaskResponseDto {
    private Long boardId;
    private String boardName;
    private Map<Long, List<Long>> columnIdAndTaskIds;
}
