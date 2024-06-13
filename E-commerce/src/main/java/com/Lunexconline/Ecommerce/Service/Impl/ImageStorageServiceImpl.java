package com.Lunexconline.Ecommerce.Service.Impl;

import com.Lunexconline.Ecommerce.Service.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private final String uploadDir = "uploads/";

    @Override
    public List<String> saveImages(List<MultipartFile> images) throws IOException {
        List<String> imagePaths = new ArrayList<>();

        for (MultipartFile image : images) {
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
            imagePaths.add(filePath.toString());
        }

        return imagePaths;
    }
}

