package com.kairav.hotelapi.dto;

import java.math.BigDecimal;

public class RoomRecommendationResponse {
    private Long roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private String category;
    private String message;
    private boolean upgraded;
    public RoomRecommendationResponse() {}
    public RoomRecommendationResponse(Long roomId,String roomNumber,String roomType,BigDecimal pricePerNight,String category,String message,boolean upgraded) {
        this.roomId =roomId;
        this.roomNumber =roomNumber;
        this.roomType =roomType;
        this.pricePerNight =pricePerNight;
        this.category =category;
        this.message =message;
        this.upgraded =upgraded;
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
    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight =pricePerNight;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category =category;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message =message;
    }
    public boolean isUpgraded() {
        return upgraded;
    }
    public void setUpgraded(boolean upgraded) {
        this.upgraded =upgraded;
    }
}
