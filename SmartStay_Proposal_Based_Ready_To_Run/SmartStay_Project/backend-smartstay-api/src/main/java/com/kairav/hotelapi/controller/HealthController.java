package com.kairav.hotelapi.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*") public class HealthController {
    @GetMapping("/health") public Map <String,String>health() {
        return Map.of("status","OK","message","Hotel API running");
    }
}
