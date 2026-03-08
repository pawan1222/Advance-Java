package com.example.Online_Course_Management_Work.service;

import com.example.Online_Course_Management_Work.dto.CourseRequestDTO;
import com.example.Online_Course_Management_Work.dto.CourseResponseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {
    CourseResponseDTO create(CourseRequestDTO request);

    CourseResponseDTO update(Long id, CourseRequestDTO request);

    void delete(Long id);

    CourseResponseDTO getById(Long id);

    Page<CourseResponseDTO> list(int page, int size, String sortBy, String sortDir);
}

