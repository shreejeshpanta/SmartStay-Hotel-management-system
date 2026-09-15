package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="hotel_services") public class HotelService {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    private String serviceName;
    private BigDecimal price;
    @Column(length =1000) private String description;
    private Boolean active =true;
    public HotelService() {}
    public HotelService(String serviceName,BigDecimal price,String description,Boolean active) {
        this.serviceName =serviceName;
        this.price =price;
        this.description =description;
        this.active =active;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName =serviceName;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price =price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description =description;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active =active;
    }
}
