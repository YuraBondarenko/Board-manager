package com.example.boardmanager.dto.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardResponseDtoTest {

    @Test
    public void dto_Ok() {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setName("name");
        boardResponseDto.setId(1L);
        BoardResponseDto newBoard = new BoardResponseDto();
        newBoard.setId(1L);
        newBoard.setName("name");
        Assertions.assertEquals(boardResponseDto, newBoard);
    }
}
