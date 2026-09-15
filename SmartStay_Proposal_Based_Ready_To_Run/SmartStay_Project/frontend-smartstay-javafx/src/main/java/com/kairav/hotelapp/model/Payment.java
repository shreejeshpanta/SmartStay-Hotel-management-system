package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Payment {
    private Long id;
    private Booking booking;
    private Customer customer;
    private String paymentDate;
    private String paymentMethod;
    private Double totalAmount;
    private Double paidAmount;
    private Double remainingAmount;
    private String paymentStatus;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking =booking;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer =customer;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate =paymentDate;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod =paymentMethod;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount =totalAmount;
    }
    public Double getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(Double paidAmount) {
        this.paidAmount =paidAmount;
    }
    public Double getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount =remainingAmount;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus =paymentStatus;
    }
    public String getCustomerName() {
        return customer ==null ?"":customer.getFullName();
    }
    public String getBookingInfo() {
        return booking ==null ?"":booking.toString();
    }
}
