package com.example.Online_Course_Management_Work.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MaterialResponseDTO {
    private Long id;
    private String title;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private Long courseId;
    private LocalDateTime uploadDate;
}

