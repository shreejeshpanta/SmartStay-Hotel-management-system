package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="users") public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    private String fullName;
    @Column(unique =true,nullable =false) private String username;
    private String password;
    @Enumerated(EnumType.STRING) private Role role;
    @Enumerated(EnumType.STRING) private Status status =Status.ACTIVE;
    private LocalDateTime createdAt =LocalDateTime.now();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password =password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role =role;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status =status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt =createdAt;
    }
}
