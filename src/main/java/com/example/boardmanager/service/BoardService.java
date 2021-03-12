package com.example.boardmanager.service;

import com.example.boardmanager.model.Board;
import java.util.List;

public interface BoardService {
    Board getById(Long id);

    Board save(Board board);

    Board delete(Long id);

    List<Board> getAll(int page, int limit, String sortBy);
}
