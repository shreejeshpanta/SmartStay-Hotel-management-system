package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.kairav.hotelapi.entity.BookingStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository <Booking,Long> {
    List <Booking>findByBookingStatus(BookingStatus status);
    long countByCreatedAtBetween(LocalDateTime start,LocalDateTime end);
    @Query("select b from Booking b where b.room.id =:roomId and b.bookingStatus in (com.kairav.hotelapi.entity.BookingStatus.CONFIRMED, com.kairav.hotelapi.entity.BookingStatus.CHECKED_IN) and b.checkInDate <:checkOut and b.checkOutDate>:checkIn") List <Booking>findOverlappingBookings(@Param("roomId") Long roomId,
    @Param("checkIn") LocalDate checkIn,
    @Param("checkOut") LocalDate checkOut);
}
