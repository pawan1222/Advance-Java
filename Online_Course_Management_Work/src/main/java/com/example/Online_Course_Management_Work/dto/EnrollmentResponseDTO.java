package com.example.Online_Course_Management_Work.dto;

import com.example.Online_Course_Management_Work.entity.EnrollmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentResponseDTO {
    private Long id;
    private Long courseId;
    private String courseTitle;
    private Long studentId;
    private String studentName;
    private EnrollmentStatus status;
    private Double progressPercentage;
    private LocalDateTime enrollmentDate;
}

