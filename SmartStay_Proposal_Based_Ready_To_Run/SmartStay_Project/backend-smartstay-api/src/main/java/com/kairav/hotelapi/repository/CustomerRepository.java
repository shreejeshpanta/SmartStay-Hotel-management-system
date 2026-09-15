package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
    List <Customer>findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(String firstName,String lastName,String phone);
}
