package com.example.boardmanager.dto.response;

import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private Long columnId;
    private String title;
    private String description;
}
