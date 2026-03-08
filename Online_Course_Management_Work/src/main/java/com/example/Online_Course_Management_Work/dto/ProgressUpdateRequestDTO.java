package com.example.Online_Course_Management_Work.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressUpdateRequestDTO {

    @NotNull(message = "Progress is required")
    @Min(value = 0, message = "Progress must be >= 0")
    @Max(value = 100, message = "Progress must be <= 100")
    private Double progressPercentage;
}

