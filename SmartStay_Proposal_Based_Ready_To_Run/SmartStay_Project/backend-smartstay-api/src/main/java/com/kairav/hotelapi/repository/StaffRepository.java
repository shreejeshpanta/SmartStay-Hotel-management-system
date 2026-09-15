package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository <Staff,Long> {}
