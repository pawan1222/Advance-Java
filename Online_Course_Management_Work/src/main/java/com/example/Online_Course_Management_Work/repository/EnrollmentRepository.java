package com.example.Online_Course_Management_Work.repository;

import com.example.Online_Course_Management_Work.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourseId(Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    Optional<Enrollment> findByCourseIdAndStudentId(Long courseId, Long studentId);
}

