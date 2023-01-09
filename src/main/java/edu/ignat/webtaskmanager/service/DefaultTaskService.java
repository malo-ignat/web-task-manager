package edu.ignat.webtaskmanager.service;

import edu.ignat.webtaskmanager.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task add(Task task) {
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        taskRepository.update(task);
        return task;
    }

    @Override
    public Task delete(Task task) {
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public List<Task> getAllUncompletedTasks() {
        return taskRepository.getAllUncompletedTasks();
    }

}
