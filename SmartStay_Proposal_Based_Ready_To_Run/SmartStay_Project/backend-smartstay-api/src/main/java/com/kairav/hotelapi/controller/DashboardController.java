package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.entity.*;
import com.kairav.hotelapi.repository.*;
import com.kairav.hotelapi.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*") public class DashboardController {
    private final RoomRepository rooms;
    private final CustomerRepository customers;
    private final BookingRepository bookings;
    private final StaffRepository staff;
    private final PaymentService payments;
    public DashboardController(RoomRepository rooms,CustomerRepository customers,BookingRepository bookings,StaffRepository staff,PaymentService payments) {
        this.rooms =rooms;
        this.customers =customers;
        this.bookings =bookings;
        this.staff =staff;
        this.payments =payments;
    }
    @GetMapping("/summary") public Map <String,Object>summary() {
        Map <String,Object>m =new LinkedHashMap<>();
        m.put("totalRooms",rooms.count());
        m.put("availableRooms",rooms.findByStatus(RoomStatus.AVAILABLE).size());
        m.put("bookedRooms",rooms.findByStatus(RoomStatus.BOOKED).size());
        m.put("occupiedRooms",rooms.findByStatus(RoomStatus.OCCUPIED).size());
        m.put("totalCustomers",customers.count());
        m.put("totalBookings",bookings.count());
        m.put("totalStaff",staff.count());
        m.put("todayRevenue",payments.todayRevenue());
        m.put("monthRevenue",payments.monthRevenue());
        return m;
    }
}
