package com.example.Online_Course_Management_Work.repository;

import com.example.Online_Course_Management_Work.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
}

