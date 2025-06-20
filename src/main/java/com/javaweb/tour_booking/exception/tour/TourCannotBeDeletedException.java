package com.javaweb.tour_booking.exception.tour;

public class TourCannotBeDeletedException extends RuntimeException {
    public TourCannotBeDeletedException(String message) {
        super(message);
    }

    public TourCannotBeDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}