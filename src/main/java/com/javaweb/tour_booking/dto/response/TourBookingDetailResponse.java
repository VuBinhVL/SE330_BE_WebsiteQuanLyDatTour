package com.javaweb.tour_booking.dto.response;


public class TourBookingDetailResponse {
    private Long id;
    private String userFullname;
    private String userMemberFullname;
    private Boolean userMemberSex;
    private String userMemberPhoneNumber;
    private String userMemberEmail;
    private Long tourBookingId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserFullname() { return userFullname; }
    public void setUserFullname(String userFullname) { this.userFullname = userFullname; }

    public String getUserMemberFullname() { return userMemberFullname; }
    public void setUserMemberFullname(String userMemberFullname) { this.userMemberFullname = userMemberFullname; }

    public Boolean getUserMemberSex() { return userMemberSex; }
    public void setUserMemberSex(Boolean userMemberSex) { this.userMemberSex = userMemberSex; }

    public String getUserMemberPhoneNumber() { return userMemberPhoneNumber; }
    public void setUserMemberPhoneNumber(String userMemberPhoneNumber) { this.userMemberPhoneNumber = userMemberPhoneNumber; }

    public String getUserMemberEmail() { return userMemberEmail; }
    public void setUserMemberEmail(String userMemberEmail) { this.userMemberEmail = userMemberEmail; }

    public Long getTourBookingId() { return tourBookingId; }
    public void setTourBookingId(Long tourBookingId) { this.tourBookingId = tourBookingId; }
}