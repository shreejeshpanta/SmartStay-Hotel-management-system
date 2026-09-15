package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository <Payment,Long> {
    List <Payment>findByBookingId(Long bookingId);
    @Query("select coalesce(sum(p.paidAmount),0) from Payment p where p.paymentDate>=:start and p.paymentDate <:end") BigDecimal revenueBetween(@Param("start") LocalDateTime start,
    @Param("end") LocalDateTime end);
}
