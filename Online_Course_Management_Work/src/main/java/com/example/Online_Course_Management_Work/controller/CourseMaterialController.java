package com.example.Online_Course_Management_Work.controller;

import com.example.Online_Course_Management_Work.dto.MaterialResponseDTO;
import com.example.Online_Course_Management_Work.dto.MaterialUploadDTO;
import com.example.Online_Course_Management_Work.service.CourseMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a course material")
    public ResponseEntity<MaterialResponseDTO> upload(
            @Valid @ModelAttribute MaterialUploadDTO request,
            @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(courseMaterialService.upload(request.getTitle(), request.getCourseId(), file));
    }

    @GetMapping("/{id}/download")
    @Operation(summary = "Download material file")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource resource = courseMaterialService.download(id);
        String fileName = courseMaterialService.getFileName(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "List materials by course")
    public ResponseEntity<List<MaterialResponseDTO>> listByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseMaterialService.getByCourseId(courseId));
    }
}

