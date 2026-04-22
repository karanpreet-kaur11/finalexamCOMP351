package com.example.studytracker.controller;

import com.example.studytracker.service.StudyTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final StudyTaskService taskService;

    public TaskController(StudyTaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) Long courseId,
                       @RequestParam(required = false) String status,
                       Model model) {
        if (status != null && status.trim().isEmpty()) status = null;
        model.addAttribute("tasks",   taskService.filter(courseId, status));
        model.addAttribute("courses", taskService.findAllCourses());
        model.addAttribute("selectedCourse", courseId);
        model.addAttribute("selectedStatus", status);
        return "tasks/list";
    }

    @GetMapping("/fragment/list")
    public String listFragment(@RequestParam(required = false) Long courseId,
                               @RequestParam(required = false) String status,
                               Model model) {
        if (status != null && status.trim().isEmpty()) status = null;
        model.addAttribute("tasks",   taskService.filter(courseId, status));
        model.addAttribute("courses", taskService.findAllCourses());
        model.addAttribute("selectedCourse", courseId);
        model.addAttribute("selectedStatus", status);
        return "fragments/task-list :: taskList";
    }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam LocalDate dueDate,
                         @RequestParam int priorityLevel,
                         @RequestParam(required = false) String notes,
                         @RequestParam Long courseId,
                         RedirectAttributes ra) {
        try {
            taskService.create(title, dueDate, priorityLevel, notes, courseId);
            ra.addFlashAttribute("message", "Task created successfully!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @PostMapping("/fragment")
    public String createFragment(@RequestParam String title,
                                 @RequestParam LocalDate dueDate,
                                 @RequestParam int priorityLevel,
                                 @RequestParam(required = false) String notes,
                                 @RequestParam Long courseId,
                                 Model model) {
        try {
            taskService.create(title, dueDate, priorityLevel, notes, courseId);
            model.addAttribute("message", "Task created successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("courses", taskService.findAllCourses());
        return "fragments/task-list :: taskList";
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam String newStatus,
                               RedirectAttributes ra) {
        try {
            taskService.updateStatus(id, newStatus);
            ra.addFlashAttribute("message", "Status updated to " + newStatus);
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        taskService.delete(id);
        ra.addFlashAttribute("message", "Task deleted.");
        return "redirect:/tasks";
    }
}