package com.example.boardmanager.service.impl;

import com.example.boardmanager.model.Board;
import com.example.boardmanager.repository.BoardRepository;
import com.example.boardmanager.service.BoardService;
import com.example.boardmanager.service.ColumnService;
import com.example.boardmanager.service.TaskService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ColumnService columnService;
    private final TaskService taskService;

    public BoardServiceImpl(BoardRepository boardRepository,
                            ColumnService columnService, TaskService taskService) {
        this.boardRepository = boardRepository;
        this.columnService = columnService;
        this.taskService = taskService;
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find board with id " + id));
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public Board delete(Long id) {
        taskService.deleteByBoardId(id);
        columnService.deleteByBoardId(id);
        Board board = getById(id);
        boardRepository.deleteById(id);
        return board;
    }

    @Override
    public List<Board> getAll(int page, int limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));
        return boardRepository.findAll(pageable).getContent();
    }
}
