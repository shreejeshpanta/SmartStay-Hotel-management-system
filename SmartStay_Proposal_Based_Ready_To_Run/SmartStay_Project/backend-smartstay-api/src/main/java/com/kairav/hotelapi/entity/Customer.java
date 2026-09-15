package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="customers") public class Customer {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    @Column(length =1000) private String address;
    private String nationality;
    private String idType;
    private String idNumber;
    private String emergencyContact;
    private LocalDateTime createdAt =LocalDateTime.now();
    public Customer() {}
    public Customer(String firstName,String lastName,String gender,String phone,String email,String address,String nationality,String idType,String idNumber,String emergencyContact) {
        this.firstName =firstName;
        this.lastName =lastName;
        this.gender =gender;
        this.phone =phone;
        this.email =email;
        this.address =address;
        this.nationality =nationality;
        this.idType =idType;
        this.idNumber =idNumber;
        this.emergencyContact =emergencyContact;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName =firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName =lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender =gender;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone =phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email =email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address =address;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality =nationality;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType =idType;
    }
    public String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber =idNumber;
    }
    public String getEmergencyContact() {
        return emergencyContact;
    }
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact =emergencyContact;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt =createdAt;
    }
    @Transient public String getFullName() {
        return (firstName ==null ?"":firstName) +" " +(lastName ==null ?"":lastName);
    }
}
