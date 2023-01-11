package edu.ignat.webtaskmanager.repository;

import edu.ignat.webtaskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SqlTaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Task t WHERE t.isCompleted = true")
    List<Task> findAllUncompletedTasks();

}
