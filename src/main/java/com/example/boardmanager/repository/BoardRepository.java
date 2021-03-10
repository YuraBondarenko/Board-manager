package com.example.boardmanager.repository;

import com.example.boardmanager.model.Board;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, BigInteger> {
}