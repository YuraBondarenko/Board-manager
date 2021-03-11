package com.example.boardmanager.controller;

import com.example.boardmanager.model.Board;
import com.example.boardmanager.service.BoardService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public Board save(@RequestBody Board board) {
        return boardService.save(board);
    }

    @GetMapping
    public List<Board> get() {
        return boardService.getAll();
    }
}
