package com.kairav.hotelapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="staff") public class Staff {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY) private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    @Column(length =1000) private String address;
    private String position;
    private BigDecimal salary;
    private String shift;
    private LocalDate joiningDate =LocalDate.now();
    @Enumerated(EnumType.STRING) private StaffStatus status =StaffStatus.ACTIVE;
    public Staff() {}
    public Staff(String firstName,String lastName,String gender,String phone,String email,String address,String position,BigDecimal salary,String shift,StaffStatus status) {
        this.firstName =firstName;
        this.lastName =lastName;
        this.gender =gender;
        this.phone =phone;
        this.email =email;
        this.address =address;
        this.position =position;
        this.salary =salary;
        this.shift =shift;
        this.status =status;
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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position =position;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary =salary;
    }
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift =shift;
    }
    public LocalDate getJoiningDate() {
        return joiningDate;
    }
    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate =joiningDate;
    }
    public StaffStatus getStatus() {
        return status;
    }
    public void setStatus(StaffStatus status) {
        this.status =status;
    }
}
