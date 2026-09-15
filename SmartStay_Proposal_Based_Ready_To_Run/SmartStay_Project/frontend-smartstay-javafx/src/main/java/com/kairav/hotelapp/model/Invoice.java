package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Invoice {
    private Long id;
    private Booking booking;
    private Customer customer;
    private Double roomCharge;
    private Double serviceCharge;
    private Double taxAmount;
    private Double totalAmount;
    private Double paidAmount;
    private Double remainingAmount;
    private String invoiceDate;
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
    public Double getRoomCharge() {
        return roomCharge;
    }
    public void setRoomCharge(Double roomCharge) {
        this.roomCharge =roomCharge;
    }
    public Double getServiceCharge() {
        return serviceCharge;
    }
    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge =serviceCharge;
    }
    public Double getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount =taxAmount;
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
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate =invoiceDate;
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
    public String getBookingSummary() {
        if (booking ==null) {
            return "";
        }
        return "#" +booking.getId() +" - Room " +booking.getRoomNumber();
    }
}
