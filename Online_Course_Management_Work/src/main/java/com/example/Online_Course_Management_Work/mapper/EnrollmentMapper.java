package com.example.Online_Course_Management_Work.mapper;

import com.example.Online_Course_Management_Work.dto.EnrollmentResponseDTO;
import com.example.Online_Course_Management_Work.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponseDTO toResponse(Enrollment enrollment) {
        return EnrollmentResponseDTO.builder()
                .id(enrollment.getId())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getFullName())
                .status(enrollment.getStatus())
                .progressPercentage(enrollment.getProgressPercentage())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }
}

