package com.example.studytracker.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String courseCode;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudyTask> tasks;

    public Course() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public List<StudyTask> getTasks() { return tasks; }
    public void setTasks(List<StudyTask> tasks) { this.tasks = tasks; }
}