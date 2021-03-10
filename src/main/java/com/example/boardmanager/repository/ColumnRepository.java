package com.example.boardmanager.repository;

import com.example.boardmanager.model.Column;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, BigInteger> {
}
