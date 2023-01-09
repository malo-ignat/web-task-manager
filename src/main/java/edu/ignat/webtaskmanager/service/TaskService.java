package edu.ignat.webtaskmanager.service;

import edu.ignat.webtaskmanager.entity.Task;

import java.util.List;

public interface TaskService {

    Task add(Task task);

    Task update(Task task);

    Task delete(Task task);

    List<Task> getAllTasks();

    List<Task> getAllUncompletedTasks();

}