package com.kairav.hotelapi.dto;

public class ApiError {
    private String error;
    public ApiError(String error) {
        this.error =error;
    }
    public String getError() {
        return error;
    }
}
