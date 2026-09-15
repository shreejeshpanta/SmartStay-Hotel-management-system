package com.kairav.hotelapi.dto;

import com.kairav.hotelapi.entity.Role;

public class LoginResponse {
    private Long userId;
    private String fullName;
    private String username;
    private Role role;
    private String message;
    public LoginResponse(Long userId,String fullName,String username,Role role,String message) {
        this.userId =userId;
        this.fullName =fullName;
        this.username =username;
        this.role =role;
        this.message =message;
    }
    public Long getUserId() {
        return userId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getUsername() {
        return username;
    }
    public Role getRole() {
        return role;
    }
    public String getMessage() {
        return message;
    }
}
