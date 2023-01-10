package edu.ignat.webtaskmanager.repository;

import edu.ignat.webtaskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SqlTaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.isCompleted = true")
    Collection<Task> findAllUncompletedTasks();

}
