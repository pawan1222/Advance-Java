package com.example.Online_Course_Management_Work.controller;

import com.example.Online_Course_Management_Work.dto.EnrollmentRequestDTO;
import com.example.Online_Course_Management_Work.dto.EnrollmentResponseDTO;
import com.example.Online_Course_Management_Work.dto.ProgressUpdateRequestDTO;
import com.example.Online_Course_Management_Work.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @Operation(summary = "Enroll student into a course")
    public ResponseEntity<EnrollmentResponseDTO> enroll(@Valid @RequestBody EnrollmentRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enroll(request));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get enrollments by student id")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getByStudentId(studentId));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get enrollments by course id")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getByCourseId(courseId));
    }

    @PutMapping("/{enrollmentId}/progress")
    @Operation(summary = "Update enrollment progress")
    public ResponseEntity<EnrollmentResponseDTO> updateProgress(
            @PathVariable Long enrollmentId,
            @Valid @RequestBody ProgressUpdateRequestDTO request) {
        return ResponseEntity.ok(enrollmentService.updateProgress(enrollmentId, request.getProgressPercentage()));
    }
}

