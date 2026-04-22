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
                {"Padlet - Online Participation",           "COMPLETED", "2026-04-10", "2", "Post weekly reflection on padlet board",          "1"},
                {"Community Building and Self-Evaluation",  "COMPLETED", "2026-04-11", "3", "Write self reflection on community involvement",  "1"},
                {"Media Literacy Video",                    "COMPLETED", "2026-02-07", "4", "Research and record media literacy analysis",     "1"},
                {"Digital Media Inventory and Detox",       "COMPLETED", "2026-03-14", "3", "Track digital usage and document detox process",  "1"},

                // CIS-395
                {"CIS-395 Session Activity",                "COMPLETED", "2026-03-30", "3", "Complete hands-on virtualization lab activity",   "2"},
                {"CIS-395 Assignment 1",                    "COMPLETED", "2026-02-02", "4", "Set up virtual machine environment",             "2"},
                {"CIS-395 Assignment 2",                    "CANCELLED", "2026-02-23", "3", "Cloud networking configuration task",            "2"},
                {"CIS-395 Assignment 3",                    "COMPLETED", "2026-03-09", "4", "Deploy application to cloud infrastructure",     "2"},
                {"CIS-395 Assignment 4",                    "COMPLETED", "2026-03-30", "4", "Configure load balancing and auto-scaling",      "2"},
                {"CIS-395 Project Submission",              "COMPLETED", "2026-03-30", "5", "Submit processing report and demo video",        "2"},

                // COMP-350
                {"COMP-350 Lab 01",                         "COMPLETED", "2026-01-21", "3", "Complete UI design basics lab",                  "3"},
                {"COMP-350 Lab 02",                         "COMPLETED", "2026-01-21", "3", "Build basic HTML CSS layout",                    "3"},
                {"COMP-350 Lab 03",                         "COMPLETED", "2026-01-28", "3", "Implement responsive design patterns",           "3"},
                {"COMP-350 Lab 04",                         "COMPLETED", "2026-02-08", "3", "JavaScript interaction and event handling",      "3"},
                {"COMP-350 Lab 05",                         "COMPLETED", "2026-02-18", "3", "Accessibility and usability testing",            "3"},
                {"COMP-350 Individual Project Proposal",    "COMPLETED", "2026-02-16", "4", "Present individual project idea with slides",    "3"},
                {"COMP-350 Quiz 1",                         "COMPLETED", "2026-02-12", "4", "Review chapters 1-4 before quiz",               "3"},
                {"COMP-350 Lab 06",                         "COMPLETED", "2026-03-25", "3", "Advanced UI components and frameworks",          "3"},
                {"COMP-350 Team Project Proposal",          "COMPLETED", "2026-03-22", "4", "Present team project proposal in class",         "3"},
                {"COMP-350 Lab 07",                         "CANCELLED", "2026-04-08", "3", "Final lab on UI testing and deployment",         "3"},
                {"COMP-350 Quiz 2",                         "COMPLETED", "2026-04-02", "4", "Review all topics from midterm onwards",         "3"},
                {"COMP-350 Project Submission",             "COMPLETED", "2026-03-08", "5", "Submit final project with report and video",     "3"},
                {"Prepare for COMP-350 Final Exam",         "PENDING",   "2026-04-23", "5", "Review all labs quizzes and project feedback",   "3"},

                // CIS-385
                {"CIS-385 Group Assignments 1-5",           "COMPLETED", "2026-02-12", "3", "Complete weekly group deliverables on time",     "4"},
                {"CIS-385 Group Assignments 6-8",           "COMPLETED", "2026-03-19", "3", "Cost estimates quality and risk analysis",       "4"},
                {"CIS-385 Assignment 9 - Resource",         "COMPLETED", "2026-03-26", "3", "Identify and document project resources",        "4"},
                {"CIS-385 Assignment 10 - Communications",  "COMPLETED", "2026-04-02", "3", "Develop project communications plan",            "4"},
                {"CIS-385 Assignment 11 - Stakeholder",     "COMPLETED", "2026-04-09", "3", "Complete stakeholder register and analysis",     "4"},
                {"CIS-385 Group Report",                    "COMPLETED", "2026-04-09", "4", "Write and submit final group project report",    "4"},
                {"CIS-385 Group PowerPoint",                "COMPLETED", "2026-04-09", "4", "Create and submit group presentation slides",    "4"},

                // COMP-351
                {"COMP-351 Assignment 1",                   "COMPLETED", "2026-02-26", "4", "Build Spring Boot REST API with endpoints",      "5"},
                {"COMP-351 Assignment 2",                   "COMPLETED", "2026-03-01", "4", "Implement REST API with full CRUD operations",   "5"},
                {"COMP-351 Assignment 3",                   "COMPLETED", "2026-03-15", "4", "JPA Hibernate entities and relationships",       "5"},
                {"COMP-351 Assignment 4",                   "COMPLETED", "2026-03-29", "4", "Spring Security session-based authentication",   "5"},
                {"COMP-351 Assignment 5 - Final Project",   "COMPLETED", "2026-04-09", "5", "Campus Store full stack Spring Boot app",        "5"},
                {"COMP-351 Final Exam - Study Tracker App", "IN_PROGRESS","2026-04-22", "5", "Build HTMX Thymeleaf Spring Boot application",  "5"},
            };

            Course[] courses = {
                macs, macs, macs, macs,
                cis395, cis395, cis395, cis395, cis395, cis395,
                comp350, comp350, comp350, comp350, comp350, comp350,
                comp350, comp350, comp350, comp350, comp350, comp350, comp350,
                cis385, cis385, cis385, cis385, cis385, cis385, cis385,
                comp351, comp351, comp351, comp351, comp351, comp351,
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