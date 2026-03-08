package com.example.Online_Course_Management_Work.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MaterialUploadDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Course id is required")
    @Positive(message = "Course id must be positive")
    private Long courseId;
}

