package com.kairav.hotelapi.service;

import com.kairav.hotelapi.dto.BookingRequest;
import com.kairav.hotelapi.entity.*;
import com.kairav.hotelapi.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service public class BookingService {
    private final BookingRepository bookings;
    private final RoomRepository rooms;
    private final CustomerRepository customers;
    public BookingService(BookingRepository bookings,RoomRepository rooms,CustomerRepository customers) {
        this.bookings =bookings;
        this.rooms =rooms;
        this.customers =customers;
    }
    public List <Booking>all() {
        return bookings.findAll();
    }
    public Booking get(Long id) {
        return bookings.findById(id).orElseThrow(() ->new RuntimeException("Booking not found"));
    }
    public Booking create(BookingRequest req) {
        Room room =rooms.findById(req.getRoomId()).orElseThrow(() ->new RuntimeException("Room not found"));
        Customer customer =customers.findById(req.getCustomerId()).orElseThrow(() ->new RuntimeException("Customer not found"));
        if (req.getCheckOutDate() ==null ||req.getCheckInDate() ==null || !req.getCheckOutDate().isAfter(req.getCheckInDate())) throw new RuntimeException("Check-out date must be after check-in date");
        if (req.getNumberOfGuests() !=null &&room.getCapacity() !=null &&req.getNumberOfGuests()>room.getCapacity()) throw new RuntimeException("Guests exceed room capacity");
        if (!bookings.findOverlappingBookings(room.getId(),req.getCheckInDate(),req.getCheckOutDate()).isEmpty()) throw new RuntimeException("Room is not available for selected dates");
        long nights =ChronoUnit.DAYS.between(req.getCheckInDate(),req.getCheckOutDate());
        BigDecimal total =room.getPricePerNight().multiply(BigDecimal.valueOf(nights));
        BigDecimal advance =req.getAdvancePayment() ==null ?BigDecimal.ZERO:req.getAdvancePayment();
        Booking b =new Booking();
        b.setCustomer(customer);
        b.setRoom(room);
        b.setCheckInDate(req.getCheckInDate());
        b.setCheckOutDate(req.getCheckOutDate());
        b.setNumberOfGuests(req.getNumberOfGuests());
        b.setTotalAmount(total);
        b.setAdvancePayment(advance);
        b.setRemainingAmount(total.subtract(advance));
        b.setSpecialRequest(req.getSpecialRequest());
        b.setBookingStatus(BookingStatus.CONFIRMED);
        room.setStatus(RoomStatus.BOOKED);
        rooms.save(room);
        return bookings.save(b);
    }
    public Booking checkIn(Long id) {
        Booking b =get(id);
        if (b.getBookingStatus() !=BookingStatus.CONFIRMED) throw new RuntimeException("Only confirmed bookings can check in");
        b.setBookingStatus(BookingStatus.CHECKED_IN);
        b.setActualCheckInDateTime(LocalDateTime.now());
        b.getRoom().setStatus(RoomStatus.OCCUPIED);
        rooms.save(b.getRoom());
        return bookings.save(b);
    }
    public Booking checkOut(Long id) {
        Booking b =get(id);
        if (b.getBookingStatus() !=BookingStatus.CHECKED_IN) throw new RuntimeException("Only checked-in bookings can check out");
        b.setBookingStatus(BookingStatus.CHECKED_OUT);
        b.setActualCheckOutDateTime(LocalDateTime.now());
        b.getRoom().setStatus(RoomStatus.CLEANING);
        rooms.save(b.getRoom());
        return bookings.save(b);
    }
    public Booking cancel(Long id) {
        Booking b =get(id);
        b.setBookingStatus(BookingStatus.CANCELLED);
        b.getRoom().setStatus(RoomStatus.AVAILABLE);
        rooms.save(b.getRoom());
        return bookings.save(b);
    }
}
