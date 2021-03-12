package com.example.boardmanager.repository;

import com.example.boardmanager.model.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> getById(Long id);
}
