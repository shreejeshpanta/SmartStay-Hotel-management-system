package com.kairav.hotelapi.dto;

import com.kairav.hotelapi.entity.PaymentMethod;
import java.math.BigDecimal;

public class PaymentRequest {
    private Long bookingId;
    private PaymentMethod paymentMethod;
    private BigDecimal paidAmount;
    public Long getBookingId() {
        return bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId =bookingId;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod =paymentMethod;
    }
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount =paidAmount;
    }
}
