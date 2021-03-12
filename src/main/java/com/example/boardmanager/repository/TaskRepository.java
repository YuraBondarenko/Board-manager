package com.example.boardmanager.repository;

import com.example.boardmanager.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "Select t from Task t join fetch"
            + " Column c on t.column.id = c.id join fetch "
            + " Board  b on b.id = c.board.id where b.id = ?1")
    List<Task> getWithRelations(Long id);

    @Transactional
    void deleteByColumnBoardId(Long id);

    @Transactional
    void deleteByColumnId(Long id);
}
