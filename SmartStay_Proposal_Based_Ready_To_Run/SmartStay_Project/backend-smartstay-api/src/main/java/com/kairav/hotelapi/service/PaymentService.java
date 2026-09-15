package com.kairav.hotelapi.service;

import com.kairav.hotelapi.dto.PaymentRequest;
import com.kairav.hotelapi.entity.*;
import com.kairav.hotelapi.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service public class PaymentService {
    private final PaymentRepository payments;
    private final BookingRepository bookings;
    public PaymentService(PaymentRepository payments,BookingRepository bookings) {
        this.payments =payments;
        this.bookings =bookings;
    }
    public List <Payment>all() {
        return payments.findAll();
    }
    public Payment pay(PaymentRequest req) {
        Booking booking =bookings.findById(req.getBookingId()).orElseThrow(() ->new RuntimeException("Booking not found"));
        BigDecimal paid =req.getPaidAmount() ==null ?BigDecimal.ZERO:req.getPaidAmount();
        BigDecimal remaining =booking.getRemainingAmount().subtract(paid);
        if (remaining.compareTo(BigDecimal.ZERO) <0) throw new RuntimeException("Paid amount cannot be greater than remaining amount");
        booking.setRemainingAmount(remaining);
        booking.setAdvancePayment(booking.getAdvancePayment().add(paid));
        bookings.save(booking);
        Payment p =new Payment();
        p.setBooking(booking);
        p.setCustomer(booking.getCustomer());
        p.setPaymentMethod(req.getPaymentMethod() ==null ?PaymentMethod.CASH:req.getPaymentMethod());
        p.setTotalAmount(booking.getTotalAmount());
        p.setPaidAmount(paid);
        p.setRemainingAmount(remaining);
        p.setPaymentStatus(remaining.compareTo(BigDecimal.ZERO) ==0 ?PaymentStatus.PAID:PaymentStatus.PARTIAL);
        return payments.save(p);
    }
    public BigDecimal todayRevenue() {
        LocalDate d =LocalDate.now();
        return payments.revenueBetween(d.atStartOfDay(),d.plusDays(1).atStartOfDay());
    }
    public BigDecimal monthRevenue() {
        LocalDate d =LocalDate.now().withDayOfMonth(1);
        return payments.revenueBetween(d.atStartOfDay(),d.plusMonths(1).atStartOfDay());
    }
}
