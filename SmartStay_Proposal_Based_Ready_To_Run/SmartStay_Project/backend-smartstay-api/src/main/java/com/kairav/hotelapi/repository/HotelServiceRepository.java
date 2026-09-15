package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository <HotelService,Long> {}
