package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.entity.*;
import com.kairav.hotelapi.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*") public class RoomController {
    private final RoomRepository repo;
    public RoomController(RoomRepository repo) {
        this.repo =repo;
    }
    @GetMapping public List <Room>all(@RequestParam(required =false) String sortBy,
    @RequestParam(required =false) RoomStatus status,
    @RequestParam(required =false) String type) {
        List <Room>list =new ArrayList<>(repo.findAll());
        if (status !=null) list =list.stream().filter(r ->r.getStatus() ==status).toList();
        if (type !=null && !type.isBlank()) list =list.stream().filter(r ->r.getRoomType() !=null &&r.getRoomType().equalsIgnoreCase(type)).toList();
        if ("priceAsc".equalsIgnoreCase(sortBy)) list =list.stream().sorted(Comparator.comparing(Room::getPricePerNight)).toList();
        if ("priceDesc".equalsIgnoreCase(sortBy)) list =list.stream().sorted(Comparator.comparing(Room::getPricePerNight).reversed()).toList();
        if ("roomNumber".equalsIgnoreCase(sortBy)) list =list.stream().sorted(Comparator.comparing(Room::getRoomNumber)).toList();
        return list;
    }
    @GetMapping("/{id}") public Room get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() ->new RuntimeException("Room not found"));
    }
    @GetMapping("/available") public List <Room>available() {
        return repo.findByStatus(RoomStatus.AVAILABLE).stream().sorted(Comparator.comparing(Room::getPricePerNight)).toList();
    }
    @PostMapping public Room add(@RequestBody Room r) {
        return repo.save(r);
    }
    @PutMapping("/{id}") public Room update(@PathVariable Long id,
    @RequestBody Room r) {
        Room x =get(id);
        x.setRoomNumber(r.getRoomNumber());
        x.setRoomType(r.getRoomType());
        x.setBedType(r.getBedType());
        x.setCapacity(r.getCapacity());
        x.setFloorNumber(r.getFloorNumber());
        x.setPricePerNight(r.getPricePerNight());
        x.setStatus(r.getStatus());
        x.setDescription(r.getDescription());
        return repo.save(x);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
