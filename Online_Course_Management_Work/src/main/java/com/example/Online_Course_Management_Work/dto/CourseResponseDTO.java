package com.example.Online_Course_Management_Work.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Double duration;
    private String level;
    private Long instructorId;
    private String instructorName;
    private LocalDateTime createdAt;
}

