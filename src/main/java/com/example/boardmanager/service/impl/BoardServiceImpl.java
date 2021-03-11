package com.example.boardmanager.service.impl;

import com.example.boardmanager.model.Board;
import com.example.boardmanager.repository.BoardRepository;
import com.example.boardmanager.service.BoardService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.getOne(id);
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board delete(Long id) {
        Board board = getById(id);
        boardRepository.delete(board);
        return board;
    }

    @Override
    public List<Board> getAll() {
        return boardRepository.findAll();
    }
}
