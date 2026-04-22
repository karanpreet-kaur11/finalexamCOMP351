package com.example.studytracker.controller;

import com.example.studytracker.service.StudyTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final StudyTaskService taskService;

    public DashboardController(StudyTaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/tasks";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTasks",     taskService.countTotal());
        model.addAttribute("completedTasks", taskService.countByStatus("COMPLETED"));
        model.addAttribute("pendingTasks",   taskService.countByStatus("PENDING"));
        model.addAttribute("overdueTasks",   taskService.countOverdue());
        model.addAttribute("tasksByCourse",  taskService.countByCourse());
        return "dashboard/dashboard";
    }
}