package com.javaweb.tour_booking.controller;

import com.javaweb.tour_booking.common.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private final String uploadPath;

    public AssetController() {
        // Khởi tạo thư mục lưu trữ ảnh
        this.uploadPath = Paths.get("wwwroot", "Uploads").toAbsolutePath().toString();
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục lưu trữ ảnh: " + uploadPath, e);
        }
    }

    @PostMapping("/upload-image")
    public ResponseEntity<ApiResponse<String>> uploadImage(@RequestParam("image") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("Bạn chưa chọn file.", null);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Tạo tên file duy nhất
            String fileName = UUID.randomUUID().toString() + getFileExtension(file.getOriginalFilename());
            Path filePath = Paths.get(uploadPath, fileName);

            // Lưu file vào thư mục
            Files.write(filePath, file.getBytes());

            // Trả về tên file hoặc URL (tùy cấu hình)
            String imageUrl = "/api/asset/view-image/" + fileName;
            ApiResponse<String> response = new ApiResponse<>("Upload ảnh thành công", imageUrl);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            ApiResponse<String> response = new ApiResponse<>("Hệ thống gặp sự cố khi upload ảnh", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/view-image/{fileName}")
    public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            if (!Files.exists(filePath)) {
                throw new RuntimeException("Không tìm thấy hình ảnh: " + fileName);
            }

            Resource resource = new UrlResource(filePath.toUri());
            String contentType = getContentType(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException("Hệ thống gặp sự cố khi tải ảnh: " + fileName, e);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getContentType(String fileName) {
        String fileExtension = getFileExtension(fileName).toLowerCase();
        return switch (fileExtension) {
            case ".jpg", ".jpeg" -> "image/jpeg";
            case ".png" -> "image/png";
            case ".gif" -> "image/gif";
            case ".bmp" -> "image/bmp";
            default -> "application/octet-stream";
        };
    }
}