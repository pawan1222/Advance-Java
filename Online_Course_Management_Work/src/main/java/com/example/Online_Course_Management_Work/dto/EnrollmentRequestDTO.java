package com.example.Online_Course_Management_Work.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {

    @NotNull(message = "Course id is required")
    @Positive(message = "Course id must be positive")
    private Long courseId;

    @NotNull(message = "Student id is required")
    @Positive(message = "Student id must be positive")
    private Long studentId;
}

