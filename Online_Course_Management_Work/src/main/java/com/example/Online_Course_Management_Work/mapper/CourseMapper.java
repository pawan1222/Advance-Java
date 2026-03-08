package com.example.Online_Course_Management_Work.mapper;

import com.example.Online_Course_Management_Work.dto.CourseResponseDTO;
import com.example.Online_Course_Management_Work.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponseDTO toResponse(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .duration(course.getDuration())
                .level(course.getLevel())
                .instructorId(course.getInstructor().getId())
                .instructorName(course.getInstructor().getFullName())
                .createdAt(course.getCreatedAt())
                .build();
    }
}

