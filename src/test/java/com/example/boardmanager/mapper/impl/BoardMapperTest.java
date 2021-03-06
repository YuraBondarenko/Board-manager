package com.example.boardmanager.mapper.impl;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import com.example.boardmanager.dto.request.BoardRequestDto;
import com.example.boardmanager.dto.response.BoardResponseDto;
import com.example.boardmanager.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void getDto_Ok() {
        Board board = new Board();
        board.setName("name");
        board.setId(1L);
        BoardResponseDto dto = boardMapper.getDto(board);
        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals(1L, dto.getId());
    }

    @Test
    void getEntity_Ok() {
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setName("name");
        Board entity = boardMapper.getEntity(boardRequestDto);
        Assertions.assertEquals("name", entity.getName());
    }
}
