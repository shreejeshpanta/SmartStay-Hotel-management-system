package com.kairav.hotelapi.dto;

import java.math.BigDecimal;

public class SmartPricingResponse {
    private Long roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal basePrice;
    private BigDecimal occupancyRate;
    private BigDecimal demandMultiplier;
    private BigDecimal smartPrice;
    private String reason;
    public SmartPricingResponse() {}
    public SmartPricingResponse(Long roomId,String roomNumber,String roomType,BigDecimal basePrice,BigDecimal occupancyRate,BigDecimal demandMultiplier,BigDecimal smartPrice,String reason) {
        this.roomId =roomId;
        this.roomNumber =roomNumber;
        this.roomType =roomType;
        this.basePrice =basePrice;
        this.occupancyRate =occupancyRate;
        this.demandMultiplier =demandMultiplier;
        this.smartPrice =smartPrice;
        this.reason =reason;
    }
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId =roomId;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber =roomNumber;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType =roomType;
    }
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice =basePrice;
    }
    public BigDecimal getOccupancyRate() {
        return occupancyRate;
    }
    public void setOccupancyRate(BigDecimal occupancyRate) {
        this.occupancyRate =occupancyRate;
    }
    public BigDecimal getDemandMultiplier() {
        return demandMultiplier;
    }
    public void setDemandMultiplier(BigDecimal demandMultiplier) {
        this.demandMultiplier =demandMultiplier;
    }
    public BigDecimal getSmartPrice() {
        return smartPrice;
    }
    public void setSmartPrice(BigDecimal smartPrice) {
        this.smartPrice =smartPrice;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason =reason;
    }
}
