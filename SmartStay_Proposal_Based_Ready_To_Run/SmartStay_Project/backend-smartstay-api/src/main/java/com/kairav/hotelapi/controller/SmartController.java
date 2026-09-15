package com.kairav.hotelapi.controller;

import com.kairav.hotelapi.dto.DemandForecastResponse;
import com.kairav.hotelapi.dto.RoomRecommendationResponse;
import com.kairav.hotelapi.dto.SmartPricingResponse;
import com.kairav.hotelapi.service.SmartAlgorithmService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/smart")
@CrossOrigin("*") public class SmartController {
    private final SmartAlgorithmService service;
    public SmartController(SmartAlgorithmService service) {
        this.service =service;
    }
    @GetMapping("/dynamic-pricing") public List <SmartPricingResponse>dynamicPricing() {
        return service.dynamicPrices();
    }
    @GetMapping("/recommend-room") public RoomRecommendationResponse recommendRoom(@RequestParam BigDecimal budget,
    @RequestParam(required =false) String category) {
        return service.recommendRoom(budget,category);
    }
    @GetMapping("/forecast-demand") public DemandForecastResponse forecastDemand() {
        return service.forecastDemand();
    }
}
