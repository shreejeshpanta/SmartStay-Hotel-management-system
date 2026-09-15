package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private String idType;
    private String idNumber;
    private String emergencyContact;
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
    public String getFullName() {
        return (firstName ==null ?"":firstName) +" " +(lastName ==null ?"":lastName);
    }
    @Override public String toString() {
        return getFullName() +" - " +phone;
    }
}
