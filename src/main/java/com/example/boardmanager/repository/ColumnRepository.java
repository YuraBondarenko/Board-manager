package com.example.boardmanager.repository;

import com.example.boardmanager.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ColumnRepository extends JpaRepository<Column, Long> {
    @Transactional
    void deleteByBoardId(Long id);
}
