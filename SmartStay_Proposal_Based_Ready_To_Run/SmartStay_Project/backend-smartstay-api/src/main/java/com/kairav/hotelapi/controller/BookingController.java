package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.dto.BookingRequest;
import com.kairav.hotelapi.entity.Booking;
import com.kairav.hotelapi.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*") public class BookingController {
    private final BookingService service;
    public BookingController(BookingService service) {
        this.service =service;
    }
    @GetMapping public List <Booking>all() {
        return service.all();
    }
    @GetMapping("/{id}") public Booking get(@PathVariable Long id) {
        return service.get(id);
    }
    @PostMapping public Booking create(@RequestBody BookingRequest r) {
        return service.create(r);
    }
    @PutMapping("/{id}/check-in") public Booking checkIn(@PathVariable Long id) {
        return service.checkIn(id);
    }
    @PutMapping("/{id}/check-out") public Booking checkOut(@PathVariable Long id) {
        return service.checkOut(id);
    }
    @PutMapping("/{id}/cancel") public Booking cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
