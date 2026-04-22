package com.example.studytracker.service;

import com.example.studytracker.entity.Course;
import com.example.studytracker.entity.StudyTask;
import com.example.studytracker.repository.CourseRepository;
import com.example.studytracker.repository.StudyTaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class StudyTaskService {

    private final StudyTaskRepository taskRepository;
    private final CourseRepository courseRepository;

    public StudyTaskService(StudyTaskRepository taskRepository,
                             CourseRepository courseRepository) {
        this.taskRepository = taskRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudyTask> filter(Long courseId, String status) {
        return taskRepository.filter(courseId, status);
    }

    public List<StudyTask> findAll() {
        return taskRepository.findAll();
    }

    public StudyTask findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    public void create(String title, LocalDate dueDate, int priorityLevel,
                       String notes, Long courseId) {

        // Validation — due date cannot be in the past
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }

        // Priority must be 1-5
        if (priorityLevel < 1 || priorityLevel > 5) {
            throw new IllegalArgumentException("Priority level must be between 1 and 5.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required.");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        StudyTask task = new StudyTask();
        task.setTitle(title.trim());
        task.setDueDate(dueDate);
        task.setPriorityLevel(priorityLevel);
        task.setNotes(notes);
        task.setCourse(course);
        task.setStatus("PENDING");
        taskRepository.save(task);
    }

    public void updateStatus(Long id, String newStatus) {
        StudyTask task = findById(id);
        String current = task.getStatus();

        // Valid transitions
        boolean valid = false;
        if (current.equals("PENDING") && (newStatus.equals("IN_PROGRESS") || newStatus.equals("CANCELLED"))) valid = true;
        if (current.equals("IN_PROGRESS") && (newStatus.equals("COMPLETED") || newStatus.equals("CANCELLED"))) valid = true;

        if (!valid) {
            throw new IllegalArgumentException(
                "Cannot change status from " + current + " to " + newStatus + ".");
        }

        task.setStatus(newStatus);
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    // Dashboard data
    public long countTotal() { return taskRepository.count(); }
    public long countByStatus(String status) { return taskRepository.countByStatus(status); }
    public long countOverdue() { return taskRepository.findOverdue(LocalDate.now()).size(); }
    public List<Object[]> countByCourse() { return taskRepository.countByCourse(); }
    public List<Course> findAllCourses() { return courseRepository.findAll(); }
}