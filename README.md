# Study Tracker — COMP 351 Final Exam
Student: Karanpreet Kaur  
Student Number: 300208472

A personal academic task management web application built with Spring Boot,
Thymeleaf, HTMX, and MySQL.

## Theme
Students can track their course assignments and study tasks across multiple
courses, set priority levels, due dates, and manage task progress through
defined status transitions.

## Technologies Used
- Java 17
- Spring Boot 3.x
- Spring MVC + Thymeleaf (server-side rendering)
- HTMX 2.0.3 (partial page updates)
- MySQL 8.x
- Spring Data JPA / Hibernate
- Bootstrap 5.3

## Two Database Tables
- **Course** (Table B) — groups tasks by course
- **StudyTask** (Table A) — main records with title, status, dueDate, priorityLevel, notes

## How to Run

### Step 1 — Start MySQL
sudo service mysql start

### Step 2 — Create Database
sudo mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS studytracker;"

### Step 3 — Configure Database
Credentials are set in:
studytracker/src/main/resources/application.properties

Default: username=root, password=root

### Step 4 — Run the Application
cd studytracker
./mvnw spring-boot:run

### Step 5 — Open in Browser
http://localhost:8080

## Seeding Method
CommandLineRunner — seed data is inserted automatically on first startup
if the database is empty. Includes 5 courses and 36 study tasks with
multiple status values.

## Features
- Add, update, delete study tasks (CRUD)
- Filter tasks by course and status using HTMX GET (partial update)
- Create tasks using HTMX POST (partial update)
- Delete tasks using form POST with hidden method DELETE
- Status transitions enforced server-side:
  - PENDING → IN_PROGRESS or CANCELLED
  - IN_PROGRESS → COMPLETED or CANCELLED
  - COMPLETED and CANCELLED are terminal states
- Domain rule: due date cannot be in the past
- Priority level must be between 1 and 5
- Dashboard with KPIs (Total, Completed, Pending, Overdue)
- Dashboard group-by table: task count per course

## HTMX Methods Used
- GET — filter tasks by course/status (fragment update)
- POST — add new task (fragment update)
- DELETE — delete task via hidden method filter

## Status Values
- PENDING (default)
- IN_PROGRESS
- COMPLETED (terminal)
- CANCELLED (terminal)