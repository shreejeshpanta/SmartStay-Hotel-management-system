package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class LoginResponse {
    private Long userId;
    private String fullName;
    private String username;
    private String role;
    private String message;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId =userId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName =fullName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username =username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role =role;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message =message;
    }
}
