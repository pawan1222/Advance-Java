package com.example.Online_Course_Management_Work.service.impl;

import com.example.Online_Course_Management_Work.dto.MaterialResponseDTO;
import com.example.Online_Course_Management_Work.entity.Course;
import com.example.Online_Course_Management_Work.entity.CourseMaterial;
import com.example.Online_Course_Management_Work.exception.FileStorageException;
import com.example.Online_Course_Management_Work.exception.ResourceNotFoundException;
import com.example.Online_Course_Management_Work.mapper.CourseMaterialMapper;
import com.example.Online_Course_Management_Work.repository.CourseMaterialRepository;
import com.example.Online_Course_Management_Work.repository.CourseRepository;
import com.example.Online_Course_Management_Work.service.CourseMaterialService;
import com.example.Online_Course_Management_Work.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository courseMaterialRepository;
    private final CourseMaterialMapper courseMaterialMapper;

    @Value("${app.file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public MaterialResponseDTO upload(String title, Long courseId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileStorageException("Uploaded file is empty");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        try {
            Path uploadPath = FileUtil.normalizeAndValidate(Paths.get(uploadDir));
            Files.createDirectories(uploadPath);

            String safeName = FileUtil.sanitizeFileName(file.getOriginalFilename());
            String storedFileName = UUID.randomUUID() + "_" + safeName;
            Path targetPath = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            CourseMaterial material = CourseMaterial.builder()
                    .title(title)
                    .fileName(storedFileName)
                    .fileType(file.getContentType() == null ? "application/octet-stream" : file.getContentType())
                    .fileUrl(targetPath.toString())
                    .course(course)
                    .build();

            return courseMaterialMapper.toResponse(courseMaterialRepository.save(material));
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file", e);
        }
    }

    @Override
    public Resource download(Long materialId) {
        CourseMaterial material = courseMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));

        try {
            // Read the file from local uploads folder.
            Path fileUrl = FileUtil.normalizeAndValidate(Paths.get(material.getFileUrl()));
            Resource resource = new UrlResource(fileUrl.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new FileStorageException("File is not readable");
            }
            return resource;
        } catch (Exception e) {
            throw new FileStorageException("Failed to read file", e);
        }
    }

    @Override
    public String getFileName(Long materialId) {
        return courseMaterialRepository.findById(materialId)
                .map(CourseMaterial::getFileName)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));
    }

    @Override
    public List<MaterialResponseDTO> getByCourseId(Long courseId) {
        List<CourseMaterial> materials = courseMaterialRepository.findByCourseId(courseId);
        List<MaterialResponseDTO> responseList = new ArrayList<>();
        for (CourseMaterial material : materials) {
            responseList.add(courseMaterialMapper.toResponse(material));
        }
        return responseList;
    }
}

