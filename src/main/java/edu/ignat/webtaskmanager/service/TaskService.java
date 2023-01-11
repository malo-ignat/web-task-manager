package edu.ignat.webtaskmanager.service;

import edu.ignat.webtaskmanager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {

    Task add(Task task);

    Task update(Task task);

    Task delete(Task task);

    Optional<Task> getById(Long id);

    Page<Task> getAllTasks(Pageable pageable);

    Page<Task> getAllUncompletedTasks(Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

}
