package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.entity.Staff;
import com.kairav.hotelapi.repository.StaffRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin("*") public class StaffController {
    private final StaffRepository repo;
    public StaffController(StaffRepository repo) {
        this.repo =repo;
    }
    @GetMapping public List <Staff>all() {
        return repo.findAll();
    }
    @PostMapping public Staff add(@RequestBody Staff s) {
        return repo.save(s);
    }
    @PutMapping("/{id}") public Staff update(@PathVariable Long id,
    @RequestBody Staff s) {
        s.setId(id);
        return repo.save(s);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
