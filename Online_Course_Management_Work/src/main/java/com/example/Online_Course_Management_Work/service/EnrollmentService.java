package com.example.Online_Course_Management_Work.service;

import com.example.Online_Course_Management_Work.dto.EnrollmentRequestDTO;
import com.example.Online_Course_Management_Work.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO request);

    List<EnrollmentResponseDTO> getByStudentId(Long studentId);

    List<EnrollmentResponseDTO> getByCourseId(Long courseId);

    EnrollmentResponseDTO updateProgress(Long enrollmentId, Double progressPercentage);
}

