package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class HotelService {
    private Long id;
    private String serviceName;
    private Double price;
    private String description;
    private Boolean active;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
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
    @Override public String toString() {
        return serviceName +" - Rs. " +price;
    }
}
