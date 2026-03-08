package com.example.Online_Course_Management_Work.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Double duration;

    @NotBlank(message = "Level is required")
    private String level;

    @NotNull(message = "Instructor id is required")
    @Positive(message = "Instructor id must be positive")
    private Long instructorId;
}

