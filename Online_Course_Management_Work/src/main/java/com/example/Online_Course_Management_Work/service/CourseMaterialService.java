package com.example.Online_Course_Management_Work.service;

import com.example.Online_Course_Management_Work.dto.MaterialResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {
    MaterialResponseDTO upload(String title, Long courseId, MultipartFile file);

    Resource download(Long materialId);

    String getFileName(Long materialId);

    List<MaterialResponseDTO> getByCourseId(Long courseId);
}

