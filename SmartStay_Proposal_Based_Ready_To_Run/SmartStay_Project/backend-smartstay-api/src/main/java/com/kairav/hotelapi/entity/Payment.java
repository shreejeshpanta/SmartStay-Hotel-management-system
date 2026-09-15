package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="payments") public class Payment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    @ManyToOne private Booking booking;
    @ManyToOne private Customer customer;
    private LocalDateTime paymentDate =LocalDateTime.now();
    @Enumerated(EnumType.STRING) private PaymentMethod paymentMethod =PaymentMethod.CASH;
    private BigDecimal totalAmount =BigDecimal.ZERO;
    private BigDecimal paidAmount =BigDecimal.ZERO;
    private BigDecimal remainingAmount =BigDecimal.ZERO;
    @Enumerated(EnumType.STRING) private PaymentStatus paymentStatus =PaymentStatus.UNPAID;
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
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate =paymentDate;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod =paymentMethod;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount =totalAmount;
    }
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount =paidAmount;
    }
    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount =remainingAmount;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus =paymentStatus;
    }
}
