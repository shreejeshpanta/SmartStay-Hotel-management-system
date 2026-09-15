package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="bookings") public class Booking {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    @ManyToOne private Customer customer;
    @ManyToOne private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numberOfGuests;
    @Enumerated(EnumType.STRING) private BookingStatus bookingStatus =BookingStatus.CONFIRMED;
    private BigDecimal totalAmount =BigDecimal.ZERO;
    private BigDecimal advancePayment =BigDecimal.ZERO;
    private BigDecimal remainingAmount =BigDecimal.ZERO;
    @Column(length =1000) private String specialRequest;
    private LocalDateTime actualCheckInDateTime;
    private LocalDateTime actualCheckOutDateTime;
    private LocalDateTime createdAt =LocalDateTime.now();
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
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus =bookingStatus;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount =totalAmount;
    }
    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }
    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment =advancePayment;
    }
    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount =remainingAmount;
    }
    public String getSpecialRequest() {
        return specialRequest;
    }
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest =specialRequest;
    }
    public LocalDateTime getActualCheckInDateTime() {
        return actualCheckInDateTime;
    }
    public void setActualCheckInDateTime(LocalDateTime actualCheckInDateTime) {
        this.actualCheckInDateTime =actualCheckInDateTime;
    }
    public LocalDateTime getActualCheckOutDateTime() {
        return actualCheckOutDateTime;
    }
    public void setActualCheckOutDateTime(LocalDateTime actualCheckOutDateTime) {
        this.actualCheckOutDateTime =actualCheckOutDateTime;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt =createdAt;
    }
}
