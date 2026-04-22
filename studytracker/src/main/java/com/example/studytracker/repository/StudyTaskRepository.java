package com.example.studytracker.repository;

import com.example.studytracker.entity.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {

    long countByStatus(String status);

    @Query("SELECT t FROM StudyTask t WHERE t.dueDate < :today AND t.status != 'COMPLETED' AND t.status != 'CANCELLED'")
    List<StudyTask> findOverdue(@Param("today") LocalDate today);

    @Query("SELECT t.course.name, COUNT(t) FROM StudyTask t GROUP BY t.course.name")
    List<Object[]> countByCourse();

    @Query("SELECT t FROM StudyTask t WHERE " +
           "(:courseId IS NULL OR t.course.id = :courseId) AND " +
           "(:status IS NULL OR :status = '' OR t.status = :status)")
    List<StudyTask> filter(
        @Param("courseId") Long courseId,
        @Param("status") String status);
}