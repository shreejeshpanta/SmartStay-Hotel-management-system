package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="rooms") public class Room {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    @Column(unique =true,nullable =false) private String roomNumber;
    private String roomType;
    private String bedType;
    private Integer capacity;
    private Integer floorNumber;
    private BigDecimal pricePerNight;
    @Enumerated(EnumType.STRING) private RoomStatus status =RoomStatus.AVAILABLE;
    @Column(length =1000) private String description;
    private LocalDateTime createdAt =LocalDateTime.now();
    public Room() {}
    public Room(String roomNumber,String roomType,String bedType,Integer capacity,Integer floorNumber,BigDecimal pricePerNight,RoomStatus status,String description) {
        this.roomNumber =roomNumber;
        this.roomType =roomType;
        this.bedType =bedType;
        this.capacity =capacity;
        this.floorNumber =floorNumber;
        this.pricePerNight =pricePerNight;
        this.status =status;
        this.description =description;
    }
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
    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight =pricePerNight;
    }
    public RoomStatus getStatus() {
        return status;
    }
    public void setStatus(RoomStatus status) {
        this.status =status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description =description;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt =createdAt;
    }
}
