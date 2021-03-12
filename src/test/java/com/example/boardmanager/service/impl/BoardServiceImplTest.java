package com.example.boardmanager.service.impl;

import com.example.boardmanager.model.Board;
import com.example.boardmanager.service.BoardService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceImplTest {
    private static final int PAGE = 0;
    private static final int LIMIT = 10;
    private static final String SORT_BY = "id";
    @Autowired
    private BoardService boardService;

    @Test
    public void get_Ok() {
        Board board = new Board();
        board.setName("name");
        boardService.save(board);
        Long id = board.getId();
        Assertions.assertEquals("name", boardService.getById(id).getName());
    }

    @Test
    public void getAll_Ok() {
        List<Board> boards = boardService.getAll(PAGE, LIMIT, SORT_BY);
        Assertions.assertEquals(0, boards.size());
        Board board = new Board();
        board.setName("name");
        boardService.save(board);
        boards = boardService.getAll(PAGE, LIMIT, SORT_BY);
        Assertions.assertEquals(1, boards.size());
    }

    @Test
    public void delete_Ok() {
        Board board = new Board();
        board.setName("name");
        boardService.save(board);
        Board newBoard = new Board();
        newBoard.setName("newName");
        boardService.save(newBoard);
        boardService.delete(board.getId());
        Assertions.assertEquals(1, boardService.getAll(PAGE, LIMIT, SORT_BY).size());
    }

    @Test
    public void update_Ok() {
        Board board = new Board();
        board.setName("old name");
        boardService.save(board);
        Long id = board.getId();
        Assertions.assertEquals("old name", boardService.getById(id).getName());
        board.setName("new name");
        boardService.save(board);
        Assertions.assertEquals("new name", boardService.getById(id).getName());
    }

    @Test
    public void get_NotOk() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> boardService.getById(100L));
    }
}
