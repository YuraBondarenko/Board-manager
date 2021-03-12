package com.example.boardmanager.mapper.impl;

import com.example.boardmanager.dto.request.BoardRequestDto;
import com.example.boardmanager.dto.response.BoardResponseDto;
import com.example.boardmanager.mapper.MapperToDto;
import com.example.boardmanager.mapper.MapperToEntity;
import com.example.boardmanager.model.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper implements MapperToDto<BoardResponseDto, Board>,
        MapperToEntity<Board, BoardRequestDto> {
    @Override
    public BoardResponseDto getDto(Board entity) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(entity.getId());
        boardResponseDto.setName(entity.getName());
        return boardResponseDto;
    }

    @Override
    public Board getEntity(BoardRequestDto dto) {
        Board board = new Board();
        board.setName(dto.getName());
        return board;
    }
}
