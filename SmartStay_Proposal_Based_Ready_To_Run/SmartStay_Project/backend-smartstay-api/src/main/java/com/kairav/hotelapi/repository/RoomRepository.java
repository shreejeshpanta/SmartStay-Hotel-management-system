package com.kairav.hotelapi.repository;

import com.kairav.hotelapi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.kairav.hotelapi.entity.RoomStatus;

public interface RoomRepository extends JpaRepository <Room,Long> {
    List <Room>findByStatus(RoomStatus status);
    Optional <Room>findByRoomNumber(String roomNumber);
}
