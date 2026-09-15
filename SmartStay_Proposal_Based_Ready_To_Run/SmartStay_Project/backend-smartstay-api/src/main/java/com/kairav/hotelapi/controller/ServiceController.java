package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.entity.HotelService;
import com.kairav.hotelapi.repository.HotelServiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*") public class ServiceController {
    private final HotelServiceRepository repo;
    public ServiceController(HotelServiceRepository repo) {
        this.repo =repo;
    }
    @GetMapping public List <HotelService>all() {
        return repo.findAll();
    }
    @PostMapping public HotelService add(@RequestBody HotelService s) {
        return repo.save(s);
    }
    @PutMapping("/{id}") public HotelService update(@PathVariable Long id,
    @RequestBody HotelService s) {
        s.setId(id);
        return repo.save(s);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
