package edu.ignat.webtaskmanager.repository;

import edu.ignat.webtaskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SqlTaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.isCompleted = true")
    Page<Task> findAllUncompletedTasks(Pageable pageable);

}
