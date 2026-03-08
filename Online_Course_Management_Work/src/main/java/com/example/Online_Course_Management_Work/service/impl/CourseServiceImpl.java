package com.example.Online_Course_Management_Work.service.impl;

import com.example.Online_Course_Management_Work.dto.CourseRequestDTO;
import com.example.Online_Course_Management_Work.dto.CourseResponseDTO;
import com.example.Online_Course_Management_Work.entity.Course;
import com.example.Online_Course_Management_Work.entity.Role;
import com.example.Online_Course_Management_Work.entity.User;
import com.example.Online_Course_Management_Work.exception.InvalidRequestException;
import com.example.Online_Course_Management_Work.exception.ResourceNotFoundException;
import com.example.Online_Course_Management_Work.mapper.CourseMapper;
import com.example.Online_Course_Management_Work.repository.CourseRepository;
import com.example.Online_Course_Management_Work.repository.UserRepository;
import com.example.Online_Course_Management_Work.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO create(CourseRequestDTO request) {
        // Validate instructor before creating the course.
        User instructor = getValidInstructor(request.getInstructorId());

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .duration(request.getDuration())
                .level(request.getLevel())
                .instructor(instructor)
                .build();

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO update(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        User instructor = getValidInstructor(request.getInstructorId());

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());
        course.setInstructor(instructor);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }

    @Override
    @Cacheable(value = "courses", key = "'course:' + #id")
    public CourseResponseDTO getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toResponse(course);
    }

    @Override
    @Cacheable(value = "courses", key = "'list:' + #page + ':' + #size + ':' + #sortBy + ':' + #sortDir")
    public Page<CourseResponseDTO> list(int page, int size, String sortBy, String sortDir) {
        Sort sort = "desc".equalsIgnoreCase(sortDir) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return courseRepository.findAll(pageable).map(courseMapper::toResponse);
    }

    private User getValidInstructor(Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));

        if (instructor.getRole() != Role.INSTRUCTOR && instructor.getRole() != Role.ADMIN) {
            throw new InvalidRequestException("User is not allowed to create/manage courses");
        }

        return instructor;
    }
}

