package com.javaweb.tour_booking.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTouristAttractionResponse {
    private Long id;
    private String name;
    private String location;
    private String categoryName;
    private String image;
}
