package com.kairav.hotelapi.service;

import com.kairav.hotelapi.dto.DemandForecastResponse;
import com.kairav.hotelapi.dto.RoomRecommendationResponse;
import com.kairav.hotelapi.dto.SmartPricingResponse;
import com.kairav.hotelapi.entity.Room;
import com.kairav.hotelapi.entity.RoomStatus;
import com.kairav.hotelapi.repository.BookingRepository;
import com.kairav.hotelapi.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service public class SmartAlgorithmService {
    private final RoomRepository rooms;
    private final BookingRepository bookings;
    public SmartAlgorithmService(RoomRepository rooms,BookingRepository bookings) {
        this.rooms =rooms;
        this.bookings =bookings;
    }
    //Dynamic Pricing
    public List <SmartPricingResponse>dynamicPrices() {
        long totalRooms =Math.max(rooms.count(),1);
        long unavailableRooms =rooms.findAll().stream().filter(r ->r.getStatus() ==RoomStatus.BOOKED ||r.getStatus() ==RoomStatus.OCCUPIED).count();
        BigDecimal occupancyRate =BigDecimal.valueOf(unavailableRooms).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalRooms),2,RoundingMode.HALF_UP);
        BigDecimal multiplier;
        String reason;
        if (occupancyRate.compareTo(BigDecimal.valueOf(80))>=0) {
            multiplier =BigDecimal.valueOf(1.25);
            reason ="High demand: occupancy is 80% or above, so price increases by 25%.";
        } else if (occupancyRate.compareTo(BigDecimal.valueOf(50))>=0) {
            multiplier =BigDecimal.valueOf(1.10);
            reason ="Medium demand: occupancy is 50% or above, so price increases by 10%.";
        } else {
            multiplier =BigDecimal.valueOf(0.95);
            reason ="Low demand: occupancy is below 50%, so price decreases by 5%.";
        }
        BigDecimal finalMultiplier =multiplier;
        String finalReason =reason;
        return rooms.findAll().stream().sorted(Comparator.comparing(Room::getPricePerNight)).map(r ->new SmartPricingResponse(r.getId(),r.getRoomNumber(),r.getRoomType(),r.getPricePerNight(),occupancyRate,finalMultiplier,r.getPricePerNight().multiply(finalMultiplier).setScale(2,RoundingMode.HALF_UP),finalReason)).toList();
    }
    // Rule-based room recommendation based on customer budget and guest category.
    public RoomRecommendationResponse recommendRoom(BigDecimal budget,String category) {
        if (budget ==null ||budget.compareTo(BigDecimal.ZERO)<=0) {
            throw new RuntimeException("Budget must be greater than zero");
        }
        List <Room>available =rooms.findByStatus(RoomStatus.AVAILABLE).stream().filter(r ->r.getPricePerNight() !=null).sorted(Comparator.comparing(Room::getPricePerNight)).toList();
        if (available.isEmpty()) {
            throw new RuntimeException("No available rooms right now");
        }
        String recommendationCategory =normalizeCategory(category);
        List <Room>affordableRooms =available.stream().filter(r ->r.getPricePerNight().compareTo(budget)<=0).toList();
        if (affordableRooms.isEmpty()) {
            throw new RuntimeException("No available rooms found within the customer budget");
        }
        Room bestWithinBudget =bestRecommendation(affordableRooms,recommendationCategory,budget);
        boolean upgraded =false;
        Room recommendation =bestWithinBudget;
        BigDecimal upgradeLimit =budget.multiply(BigDecimal.valueOf(1.15));
        List <Room>upgradeRooms =available.stream().filter(r ->r.getPricePerNight().compareTo(bestWithinBudget.getPricePerNight())>0 &&r.getPricePerNight().compareTo(upgradeLimit)<=0).toList();
        if (!upgradeRooms.isEmpty()) {
            Room upgrade =bestRecommendation(upgradeRooms,recommendationCategory,upgradeLimit);
            if (categoryScore(upgrade,recommendationCategory)>categoryScore(bestWithinBudget,recommendationCategory) ||categoryScore(upgrade,recommendationCategory) ==categoryScore(bestWithinBudget,recommendationCategory) &&upgrade.getPricePerNight().compareTo(bestWithinBudget.getPricePerNight())>0) {
                recommendation =upgrade;
                upgraded =true;
            }
        }
        String message =upgraded ?"Auto-upgrade applied: better " +recommendationCategory.toLowerCase() +" room found within 15% above customer budget.":"Recommended best available room for " +recommendationCategory.toLowerCase() +" preference and customer budget.";
        return new RoomRecommendationResponse(recommendation.getId(),recommendation.getRoomNumber(),recommendation.getRoomType(),recommendation.getPricePerNight(),recommendationCategory,message,upgraded);
    }
    public RoomRecommendationResponse recommendRoom(BigDecimal budget) {
        return recommendRoom(budget,"Best Value");
    }
    private String normalizeCategory(String category) {
        if (category ==null ||category.isBlank()) return "Best Value";
        String cleaned =category.trim().toLowerCase();
        if (cleaned.equals("family")) return "Family";
        if (cleaned.equals("business")) return "Business";
        if (cleaned.equals("luxury")) return "Luxury";
        if (cleaned.equals("quiet")) return "Quiet";
        if (cleaned.equals("group")) return "Group";
        return "Best Value";
    }
    private Room bestRecommendation(List <Room>candidateRooms,String category,BigDecimal priceLimit) {
        return candidateRooms.stream().max((left,right) ->compareRecommendation(left,right,category,priceLimit)).orElseThrow(() ->new RuntimeException("No matching room found"));
    }
    private int compareRecommendation(Room left,Room right,String category,BigDecimal priceLimit) {
        int categoryCompare =Integer.compare(categoryScore(left,category),categoryScore(right,category));
        if (categoryCompare !=0) return categoryCompare;

        int priceCompare =left.getPricePerNight().compareTo(right.getPricePerNight());
        if ("Best Value".equals(category)) return priceCompare;

        int budgetFitCompare =left.getPricePerNight().subtract(priceLimit).abs().compareTo(right.getPricePerNight().subtract(priceLimit).abs());
        if (budgetFitCompare !=0) return -budgetFitCompare;

        return priceCompare;
    }
    private int categoryScore(Room room,String category) {
        int score =0;
        String searchable =((room.getRoomType() ==null ?"":room.getRoomType()) +" " +(room.getBedType() ==null ?"":room.getBedType()) +" " +(room.getDescription() ==null ?"":room.getDescription())).toLowerCase();
        int capacity =room.getCapacity() ==null ?0:room.getCapacity();
        int floor =room.getFloorNumber() ==null ?0:room.getFloorNumber();
        if ("Family".equals(category)) {
            score +=capacity >=4 ?60:capacity >=2 ?25:0;
            score +=containsAny(searchable,"family","suite","double") ?35:0;
        } else if ("Business".equals(category)) {
            score +=containsAny(searchable,"business","quiet","single","deluxe") ?45:0;
            score +=capacity <=2 ?25:0;
            score +=floor >0 &&floor <=2 ?15:0;
        } else if ("Luxury".equals(category)) {
            score +=containsAny(searchable,"suite","premium","deluxe","king","view") ?60:0;
            score +=room.getPricePerNight().compareTo(BigDecimal.valueOf(3500))>=0 ?25:0;
        } else if ("Quiet".equals(category)) {
            score +=containsAny(searchable,"quiet","business") ?60:0;
            score +=floor >1 ?20:0;
            score +=capacity <=2 ?10:0;
        } else if ("Group".equals(category)) {
            score +=capacity >=4 ?70:capacity >=2 ?30:0;
            score +=containsAny(searchable,"suite","family","double") ?20:0;
        } else {
            score +=0;
        }
        return score;
    }
    private boolean containsAny(String value,String... words) {
        for (String word:words) {
            if (value.contains(word)) return true;
        }
        return false;
    }
    // Moving average method from proposal.
    public DemandForecastResponse forecastDemand() {
        LocalDateTime now =LocalDateTime.now();
        long last7Days =bookings.countByCreatedAtBetween(now.minusDays(7),now);
        double avg =last7Days /7.0;
        long next7 =Math.round(avg *7);
        String recommendation =next7>=rooms.count() *0.7 ?"High predicted demand: use dynamic pricing and prepare staff for busy days.":"Normal predicted demand: continue regular pricing and monitor reservations.";
        return new DemandForecastResponse(last7Days,Math.round(avg *100.0) /100.0,next7,recommendation);
    }
}
