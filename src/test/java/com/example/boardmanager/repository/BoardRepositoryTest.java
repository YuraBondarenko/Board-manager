package com.example.boardmanager.repository;

import com.example.boardmanager.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardRepositoryTest {
    private static final int PAGE = 0;
    private static final int LIMIT = 10;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void get_Ok() {
        Board board = boardRepository.save(new Board());
        Assertions.assertEquals(board, boardRepository.getOne(board.getId()));
        Assertions.assertEquals(board.getId(), boardRepository.getById(board.getId())
                .get().getId());
    }

    @Test
    public void delete_Ok() {
        Board board = new Board();
        board.setName("delete");
        boardRepository.save(board);
        Assertions.assertEquals(1L,
                boardRepository.findAll(PageRequest.of(PAGE, LIMIT)).getContent().size());
        boardRepository.delete(board);
        Assertions.assertEquals(0L, boardRepository
                .findAll(PageRequest.of(PAGE, LIMIT)).getContent().size());
    }
}
