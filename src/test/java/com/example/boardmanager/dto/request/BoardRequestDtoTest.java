package com.example.boardmanager.dto.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardRequestDtoTest {
    @Test
    public void dto_Ok() {
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setName("name");
        Assertions.assertEquals("name", boardRequestDto.getName());
    }
}
