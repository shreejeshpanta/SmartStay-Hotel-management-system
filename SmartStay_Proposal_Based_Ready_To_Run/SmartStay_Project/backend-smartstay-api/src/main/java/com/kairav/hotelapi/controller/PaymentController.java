package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.dto.PaymentRequest;
import com.kairav.hotelapi.entity.Payment;
import com.kairav.hotelapi.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*") public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service) {
        this.service =service;
    }
    @GetMapping public List <Payment>all() {
        return service.all();
    }
    @PostMapping public Payment pay(@RequestBody PaymentRequest r) {
        return service.pay(r);
    }
    @GetMapping("/revenue/today") public BigDecimal today() {
        return service.todayRevenue();
    }
    @GetMapping("/revenue/month") public BigDecimal month() {
        return service.monthRevenue();
    }
}
