package edu.ignat.webtaskmanager.service;

import edu.ignat.webtaskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task add(Task task);

    Task update(Task task);

    Task delete(Task task);

    Optional<Task> getById(Long id);

    List<Task> getAllTasks();

    List<Task> getAllUncompletedTasks();

    List<Task> findByTitleContainingIgnoreCase(String keyword);

}
