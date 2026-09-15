package com.kairav.hotelapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingRequest {
    private Long customerId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numberOfGuests;
    private BigDecimal advancePayment;
    private String specialRequest;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId =customerId;
    }
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId =roomId;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate =checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate =checkOutDate;
    }
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }
    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests =numberOfGuests;
    }
    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }
    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment =advancePayment;
    }
    public String getSpecialRequest() {
        return specialRequest;
    }
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest =specialRequest;
    }
}
