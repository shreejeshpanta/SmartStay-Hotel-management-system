package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Booking {
    private Long id;
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private Integer numberOfGuests;
    private String bookingStatus;
    private Double totalAmount;
    private Double advancePayment;
    private Double remainingAmount;
    private String specialRequest;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer =customer;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room =room;
    }
    public String getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(String checkInDate) {
        this.checkInDate =checkInDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate =checkOutDate;
    }
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }
    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests =numberOfGuests;
    }
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus =bookingStatus;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount =totalAmount;
    }
    public Double getAdvancePayment() {
        return advancePayment;
    }
    public void setAdvancePayment(Double advancePayment) {
        this.advancePayment =advancePayment;
    }
    public Double getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount =remainingAmount;
    }
    public String getSpecialRequest() {
        return specialRequest;
    }
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest =specialRequest;
    }
    public String getCustomerName() {
        return customer ==null ?"":customer.getFullName();
    }
    public String getRoomNumber() {
        return room ==null ?"":room.getRoomNumber();
    }
    @Override public String toString() {
        return "#" +id +" - " +getCustomerName() +" - Room " +getRoomNumber() +" - Remaining Rs. " +remainingAmount;
    }
}
