package com.example.boardmanager.dto.request;

import lombok.Data;

@Data
public class TaskRequestDto {
    private Long columnId;
    private String title;
    private String description;
}
