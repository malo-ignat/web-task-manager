package edu.ignat.webtaskmanager.controller;

import edu.ignat.webtaskmanager.entity.Task;
import edu.ignat.webtaskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<Task> tasks;
            Pageable paging = PageRequest.of(page - 1, size);

            Page<Task> pageTasks;
            if (keyword == null) {
                pageTasks = taskService.getAllTasks(paging);
            } else {
                pageTasks = taskService.findByTitleContainingIgnoreCase(keyword, paging);
                model.addAttribute("keyword", keyword);
            }

            tasks = pageTasks.getContent();

            model.addAttribute("tasks", tasks);
            model.addAttribute("currentPage", pageTasks.getNumber() + 1);
            model.addAttribute("totalItems", pageTasks.getTotalElements());
            model.addAttribute("totalPages", pageTasks.getTotalPages());
            model.addAttribute("pageSize", size);
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
    public String updateTutorialPublishedStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status,
                                                Model model, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskService.getById(id).get();
            task.setCompleted(status);
            taskService.update(task);

            String statusName = status ? "completed" : "uncompleted";
            String message = "The task id=" + id + " has been " + statusName;
            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }

}
