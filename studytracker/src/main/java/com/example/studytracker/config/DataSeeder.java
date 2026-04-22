package com.example.studytracker.config;

import com.example.studytracker.entity.Course;
import com.example.studytracker.entity.StudyTask;
import com.example.studytracker.repository.CourseRepository;
import com.example.studytracker.repository.StudyTaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(CourseRepository courseRepository,
                                StudyTaskRepository taskRepository) {
        return args -> {
            if (courseRepository.count() > 0) return;

            Course macs = new Course();
            macs.setName("Intro to Communication Studies");
            macs.setCourseCode("MACS-110");
            courseRepository.save(macs);

            Course cis395 = new Course();
            cis395.setName("Virtualization and Cloud Infrastructure");
            cis395.setCourseCode("CIS-395");
            courseRepository.save(cis395);

            Course comp350 = new Course();
            comp350.setName("User Interface Design and Programming");
            comp350.setCourseCode("COMP-350");
            courseRepository.save(comp350);

            Course cis385 = new Course();
            cis385.setName("Project Management");
            cis385.setCourseCode("CIS-385");
            courseRepository.save(cis385);

            Course comp351 = new Course();
            comp351.setName("Advanced Website Programming");
            comp351.setCourseCode("COMP-351");
            courseRepository.save(comp351);

            String[][] tasks = {
            // MACS-110
            {"Padlet - Online Participation",           "COMPLETED",   "2026-04-10", "2", "Post weekly reflection on padlet board",        "1"},
            {"Digital Media Inventory and Detox",       "COMPLETED",   "2026-03-14", "3", "Track digital usage and document detox process","1"},

            // CIS-395
            {"CIS-395 Assignment 3",                    "COMPLETED",   "2026-03-09", "4", "Deploy application to cloud infrastructure",    "2"},
            {"CIS-395 Project Submission",              "COMPLETED",   "2026-03-30", "5", "Submit processing report and demo video",       "2"},

            // COMP-350
            {"COMP-350 Quiz 2",                         "COMPLETED",   "2026-04-02", "4", "Review all topics from midterm onwards",        "3"},
            {"Prepare for COMP-350 Final Exam",         "PENDING",     "2026-04-23", "5", "Review all labs quizzes and project feedback",  "3"},

            // CIS-385
            {"CIS-385 Assignment 11 - Stakeholder",     "COMPLETED",   "2026-04-09", "3", "Complete stakeholder register and analysis",   "4"},
            {"CIS-385 Group Report",                    "COMPLETED",   "2026-04-09", "4", "Write and submit final group project report",   "4"},

            // COMP-351
            {"COMP-351 Assignment 5 - Final Project",   "COMPLETED",   "2026-04-09", "5", "Campus Store full stack Spring Boot app",       "5"},
            {"COMP-351 Final Exam - Study Tracker App", "IN_PROGRESS", "2026-04-22", "5", "Build HTMX Thymeleaf Spring Boot application",  "5"},
        };

        Course[] courses = {
            macs, macs,
            cis395, cis395,
            comp350, comp350,
            cis385, cis385,
            comp351, comp351,
        };

            for (int i = 0; i < tasks.length; i++) {
                StudyTask t = new StudyTask();
                t.setTitle(tasks[i][0]);
                t.setStatus(tasks[i][1]);
                t.setDueDate(LocalDate.parse(tasks[i][2]));
                t.setPriorityLevel(Integer.parseInt(tasks[i][3]));
                t.setNotes(tasks[i][4]);
                t.setCourse(courses[i]);
                t.setCreatedAt(LocalDateTime.now());
                taskRepository.save(t);
            }

            System.out.println("=== Study Tracker seed data loaded ===");
        };
    }
}