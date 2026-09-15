package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Room {
    private Long id;
    private String roomNumber;
    private String roomType;
    private String bedType;
    private Integer capacity;
    private Integer floorNumber;
    private Double pricePerNight;
    private String status;
    private String description;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
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
    public String getBedType() {
        return bedType;
    }
    public void setBedType(String bedType) {
        this.bedType =bedType;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity =capacity;
    }
    public Integer getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber =floorNumber;
    }
    public Double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight =pricePerNight;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status =status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description =description;
    }
    @Override public String toString() {
        return roomNumber +" - " +roomType;
    }
}
