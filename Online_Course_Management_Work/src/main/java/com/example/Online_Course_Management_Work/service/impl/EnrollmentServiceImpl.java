package com.example.Online_Course_Management_Work.service.impl;

import com.example.Online_Course_Management_Work.dto.EnrollmentRequestDTO;
import com.example.Online_Course_Management_Work.dto.EnrollmentResponseDTO;
import com.example.Online_Course_Management_Work.entity.*;
import com.example.Online_Course_Management_Work.exception.InvalidRequestException;
import com.example.Online_Course_Management_Work.exception.ResourceNotFoundException;
import com.example.Online_Course_Management_Work.mapper.EnrollmentMapper;
import com.example.Online_Course_Management_Work.repository.CourseRepository;
import com.example.Online_Course_Management_Work.repository.EnrollmentRepository;
import com.example.Online_Course_Management_Work.repository.UserRepository;
import com.example.Online_Course_Management_Work.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + request.getCourseId()));

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + request.getStudentId()));

        if (student.getRole() != Role.STUDENT) {
            throw new InvalidRequestException("Only users with STUDENT role can enroll");
        }

        enrollmentRepository.findByCourseIdAndStudentId(course.getId(), student.getId()).ifPresent(existing -> {
            throw new InvalidRequestException("Student already enrolled in this course");
        });

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .student(student)
                .status(EnrollmentStatus.ENROLLED)
                .progressPercentage(0.0)
                .build();

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDTO> getByStudentId(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<EnrollmentResponseDTO> responses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            responses.add(enrollmentMapper.toResponse(enrollment));
        }
        return responses;
    }

    @Override
    public List<EnrollmentResponseDTO> getByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        List<EnrollmentResponseDTO> responses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            responses.add(enrollmentMapper.toResponse(enrollment));
        }
        return responses;
    }

    @Override
    public EnrollmentResponseDTO updateProgress(Long enrollmentId, Double progressPercentage) {
        if (progressPercentage < 0 || progressPercentage > 100) {
            throw new InvalidRequestException("Progress should be between 0 and 100");
        }

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setProgressPercentage(progressPercentage);
        if (progressPercentage == 100.0) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
        } else if (progressPercentage > 0) {
            enrollment.setStatus(EnrollmentStatus.IN_PROGRESS);
        }

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }
}

