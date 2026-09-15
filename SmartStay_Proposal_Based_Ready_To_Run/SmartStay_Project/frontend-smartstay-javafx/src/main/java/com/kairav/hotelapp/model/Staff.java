package com.kairav.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true) public class Staff {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String position;
    private Double salary;
    private String shift;
    private String status;
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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position =position;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary =salary;
    }
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift =shift;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status =status;
    }
    public String getFullName() {
        return (firstName ==null ?"":firstName) +" " +(lastName ==null ?"":lastName);
    }
    @Override public String toString() {
        return getFullName() +" - " +position;
    }
}
