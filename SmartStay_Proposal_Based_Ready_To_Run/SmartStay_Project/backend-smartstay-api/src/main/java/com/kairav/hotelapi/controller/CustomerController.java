package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.entity.Customer;
import com.kairav.hotelapi.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*") public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo) {
        this.repo =repo;
    }
    @GetMapping public List <Customer>all() {
        return repo.findAll();
    }
    @GetMapping("/{id}") public Customer get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() ->new RuntimeException("Customer not found"));
    }
    @GetMapping("/search") public List <Customer>search(@RequestParam String keyword) {
        return repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(keyword,keyword,keyword);
    }
    @PostMapping public Customer add(@RequestBody Customer c) {
        return repo.save(c);
    }
    @PutMapping("/{id}") public Customer update(@PathVariable Long id,
    @RequestBody Customer c) {
        c.setId(id);
        return repo.save(c);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
