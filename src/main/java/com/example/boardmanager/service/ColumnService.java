package com.example.boardmanager.service;

import com.example.boardmanager.model.Column;

public interface ColumnService {
    Column save(Column column);

    Column delete(Long id);

    void deleteByBoardId(Long id);

    Column getById(Long id);
}
