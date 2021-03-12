package com.example.boardmanager.mapper.impl;

import com.example.boardmanager.dto.request.ColumnRequestDto;
import com.example.boardmanager.dto.response.ColumnResponseDto;
import com.example.boardmanager.mapper.MapperToDto;
import com.example.boardmanager.mapper.MapperToEntity;
import com.example.boardmanager.model.Column;
import org.springframework.stereotype.Component;

@Component
public class ColumnMapper implements MapperToDto<ColumnResponseDto, Column>,
        MapperToEntity<Column, ColumnRequestDto> {
    @Override
    public ColumnResponseDto getDto(Column entity) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId(entity.getId());
        if (entity.getBoard() != null) {
            columnResponseDto.setBoardId(entity.getBoard().getId());
        }
        columnResponseDto.setTitle(entity.getTitle());
        return columnResponseDto;
    }

    @Override
    public Column getEntity(ColumnRequestDto dto) {
        Column column = new Column();
        column.setTitle(dto.getTitle());
        return column;
    }
}
