package com.example.boardmanager.repository;

import com.example.boardmanager.model.Task;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, BigInteger> {
}
