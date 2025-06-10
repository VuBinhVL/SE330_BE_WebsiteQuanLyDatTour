package com.javaweb.tour_booking.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    public String saveOrUpdateAvatar(Long customerId, MultipartFile file) throws IOException {
        // Get absolute path to uploads/avatars
        String uploadDir = Paths.get("uploads", "avatars").toAbsolutePath().toString();
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Get file extension
        String originalName = file.getOriginalFilename();
        String ext = "";
        int i = originalName.lastIndexOf('.');
        if (i > 0) ext = originalName.substring(i);

        // Save as {customerId}.ext
        String fileName = customerId + ext;
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Save file
        file.transferTo(new File(filePath));

        // Return public URL
        return "/uploads/avatars/" + fileName;
    }
}