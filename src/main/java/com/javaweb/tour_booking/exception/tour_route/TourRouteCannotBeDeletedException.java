package com.javaweb.tour_booking.exception.tour_route;

public class TourRouteCannotBeDeletedException extends RuntimeException{
    public TourRouteCannotBeDeletedException(String message) {
        super(message);
    }

    public TourRouteCannotBeDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
