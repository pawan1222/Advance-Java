package com.example.Online_Course_Management_Work.util;

import java.nio.file.Path;

public final class FileUtil {

    private FileUtil() {
    }

    public static String sanitizeFileName(String originalName) {
        if (originalName == null || originalName.isBlank()) {
            return "file";
        }
        return originalName.replace("..", "").replace("/", "_").replace("\\", "_");
    }

    public static Path normalizeAndValidate(Path path) {
        return path.normalize().toAbsolutePath();
    }
}

