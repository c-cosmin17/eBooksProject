package com.bookstore.be.service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUtils {

    public String saveImage(MultipartFile file) {
        try {
            Path uploadPath = Path.of("C:\\Users\\Alin\\Desktop\\Facultate\\UT\\An3\\IS\\BE\\src\\main\\resources\\covers");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique filename or use the original filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Copy the file to the upload directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName; // Return the filename or the full path based on your needs
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    public String savePDF(MultipartFile file) {
        try {
            Path uploadPath = Path.of("C:\\Users\\Alin\\Desktop\\Facultate\\UT\\An3\\IS\\BE\\src\\main\\resources\\covers");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique filename or use the original filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Copy the file to the upload directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName; // Return the filename or the full path based on your needs
        } catch (IOException e) {
            throw new RuntimeException("Failed to save pdf", e);
        }
    }
}
