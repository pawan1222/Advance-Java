package com.example.Online_Course_Management_Work.mapper;

import com.example.Online_Course_Management_Work.dto.MaterialResponseDTO;
import com.example.Online_Course_Management_Work.entity.CourseMaterial;
import org.springframework.stereotype.Component;

@Component
public class CourseMaterialMapper {

    public MaterialResponseDTO toResponse(CourseMaterial material) {
        return MaterialResponseDTO.builder()
                .id(material.getId())
                .title(material.getTitle())
                .fileName(material.getFileName())
                .fileType(material.getFileType())
                .fileUrl(material.getFileUrl())
                .courseId(material.getCourse().getId())
                .uploadDate(material.getUploadDate())
                .build();
    }
}

