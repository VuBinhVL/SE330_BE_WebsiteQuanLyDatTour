package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.TouristAttractionUpdateRequest;
import com.javaweb.tour_booking.dto.response.GalleryResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.Category;
import com.javaweb.tour_booking.entity.Galley;
import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.repository.CategoryRepository;
import com.javaweb.tour_booking.repository.GalleyRepository;
import com.javaweb.tour_booking.repository.TourRouteAttractionRepository;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TouristAttractionServiceImpl implements ITouristAttractionService {
    private CategoryRepository categoryRepository;
    private TouristAttractionRepository touristAttractionRepository;
    private GalleyRepository galleyRepository;
    private TourRouteAttractionRepository tourRouteAttractionRepository;

    @Override
    public List<TouristAttractionResponse> getAllTouristAttractions() {
        return touristAttractionRepository.findAll().stream()
                .map(attraction -> new TouristAttractionResponse(
                        attraction.getId(),
                        attraction.getName(),
                        attraction.getLocation(),
                        attraction.getCategory().getName()
                )).collect(Collectors.toList());
    }

    @Override
    public TouristAttractionDetailResponse getTouristAttractionById(Long id) {
        TouristAttraction touristAttraction = touristAttractionRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch"));

        //Lấy danh sách ảnh của địa điểm
        List<GalleryResponse> images = galleyRepository.findByTouristAttraction(touristAttraction)
                .stream()
                .map(g -> {
                    String url = g.getThumbNail();
                    return new GalleryResponse(g.getId(), url);
                })
                .collect(Collectors.toList());

        return new TouristAttractionDetailResponse(
                touristAttraction.getId(),
                touristAttraction.getName(),
                touristAttraction.getLocation(),
                touristAttraction.getCategory().getName(),
                touristAttraction.getCategory().getId(),
                touristAttraction.getDescription(),
                images
        );

    }

    @Transactional
    @Override
    public void deleteTouristAttractionById(Long id) {
        TouristAttraction attraction = touristAttractionRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch"));
        //Kiểm tra ràng buộc
        List<TourRouteAttraction> linkRoutes = tourRouteAttractionRepository.findByTouristAttraction(attraction);
        if (!linkRoutes.isEmpty()) {
            throw new RuntimeException("Không thể xóa. Địa điểm đang được sử dụng trong tuyến du lịch.");
        }

        //Xóa ảnh
        galleyRepository.deleteByTouristAttraction(attraction);

        //Xóa địa điểm
        touristAttractionRepository.delete(attraction);
    }

    @Override
    @Transactional
    public void createTouristAttraction(String name,
                                        String location,
                                        String description,
                                        Long categoryId,
                                        List<MultipartFile> images) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại địa điểm"));

        TouristAttraction ta = new TouristAttraction();
        ta.setName(name);
        ta.setLocation(location);
        ta.setDescription(description);
        ta.setCategory(category);
        ta.setCreatedAt(LocalDateTime.now());
        ta.setUpdatedAt(LocalDateTime.now());
        TouristAttraction saved = touristAttractionRepository.save(ta);

        // 1. Chuẩn bị thư mục uploads (absolute path)
        Path uploadPath = Paths.get("uploads", "destinations").toAbsolutePath();
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục ", e);
        }

        // 2. Lặp qua từng file và copy
        for (MultipartFile file : images) {
            if (file.isEmpty()) continue;
            String original = file.getOriginalFilename();
            String filename = System.currentTimeMillis() + "_" + original.replaceAll("\\s+", "_");
            Path dest = uploadPath.resolve(filename);

            try (InputStream in = file.getInputStream()) {
                Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu ảnh: " + original, e);
            }

            // 3. Lưu vào DB
            Galley g = new Galley();
            g.setThumbNail("/uploads/destinations/" + filename);
            g.setTouristAttraction(saved);
            galleyRepository.save(g);
        }
    }

    @Transactional
    @Override
    public void updateTouristAttraction(Long id, TouristAttractionUpdateRequest req) {
        //1. Lấy địa điểm và category
        TouristAttraction ta = touristAttractionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa điểm"));
        Category ct = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại địa điềm"));

        //2.Cập nhật data
        ta.setCategory(ct);
        ta.setName(req.getName());
        ta.setLocation(req.getLocation());
        ta.setDescription(req.getDescription());
        ta.setUpdatedAt(LocalDateTime.now());
        touristAttractionRepository.save(ta);

}
}
