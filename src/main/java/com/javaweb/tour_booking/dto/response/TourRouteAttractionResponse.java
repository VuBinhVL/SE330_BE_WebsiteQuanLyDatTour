package com.javaweb.tour_booking.dto.response;

import com.javaweb.tour_booking.entity.Category;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.TouristAttraction;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TourRouteAttractionResponse {

    private Long id;
    private TourRoute tourRoute;
    private TouristAttraction touristAttraction;
    private Category category;
    private int orderAction;
    private String actionDescription;
    private int day;

    // Thêm getter và setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TourRoute getTourRoute() { return tourRoute; }
    public void setTourRoute(TourRoute tourRoute) { this.tourRoute = tourRoute; }
    public TouristAttraction getTouristAttraction() { return touristAttraction; }
    public void setTouristAttraction(TouristAttraction touristAttraction) { this.touristAttraction = touristAttraction; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public int getOrderAction() { return orderAction; }
    public void setOrderAction(int orderAction) { this.orderAction = orderAction; }
    public String getActionDescription() { return actionDescription; }
    public void setActionDescription(String actionDescription) { this.actionDescription = actionDescription; }
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
}