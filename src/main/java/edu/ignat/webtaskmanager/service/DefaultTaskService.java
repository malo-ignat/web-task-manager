package edu.ignat.webtaskmanager.service;

import edu.ignat.webtaskmanager.entity.Task;
import edu.ignat.webtaskmanager.repository.SqlTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultTaskService implements TaskService {

    private final SqlTaskRepository taskRepository;

    @Override
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        Task updatedTask = taskRepository.getById(task.getId());
        return taskRepository.save(updatedTask);
    }

    @Override
    public Task delete(Task task) {
        taskRepository.delete(task);
        return task;
    }

    @Override
    public Collection<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Collection<Task> getAllUncompletedTasks() {
        return taskRepository.findAllUncompletedTasks();
    }

}
