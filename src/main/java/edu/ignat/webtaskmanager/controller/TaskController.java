package edu.ignat.webtaskmanager.controller;

import edu.ignat.webtaskmanager.entity.Task;
import edu.ignat.webtaskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model, @Param("keyword") String keyword) {
        try {
            List<Task> tasks;

            if (keyword == null) {
                tasks = taskService.getAllTasks();
            } else {
                tasks = taskService.findByTitleContainingIgnoreCase(keyword);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("tasks", tasks);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        model.addAttribute("pageTitle", "Create new task");
        return "task_form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(Task task, RedirectAttributes redirectAttributes) {
        try {
            taskService.add(task);
            redirectAttributes.addFlashAttribute("message", "The task has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String editTask(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskService.getById(id).get();
            model.addAttribute("task", task);
            model.addAttribute("pageTitle", "Edit task");
            return "task_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/tasks";
        }
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskService.getById(id).get();
            taskService.delete(task);
            redirectAttributes.addFlashAttribute("message", "The task with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/completed/{status}")
    public String updateTaskStatus(@PathVariable("id") Long id, @PathVariable("status") boolean completed,
                                   Model model, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskService.getById(id).get();
            taskService.update(task);
            String status = completed ? "completed" : "uncompleted";
            String message = "The task id=" + id + " has been " + status;
            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }

}
