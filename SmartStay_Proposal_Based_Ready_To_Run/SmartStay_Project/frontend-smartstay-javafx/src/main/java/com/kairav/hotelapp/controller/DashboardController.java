package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import javafx.fxml.*;
import javafx.scene.control.Label;
import java.util.*;

public class DashboardController {
    @FXML private Label totalRooms,availableRooms,bookedRooms,occupiedRooms,totalCustomers,totalBookings,totalStaff,todayRevenue,monthRevenue;
    @FXML public void initialize() {
        refresh();
    }
    @FXML public void refresh() {
        try {
            Map <String,Object>m =ApiClient.get("/dashboard/summary",new TypeReference <Map <String,Object>>() {});
            totalRooms.setText(String.valueOf(m.get("totalRooms")));
            availableRooms.setText(String.valueOf(m.get("availableRooms")));
            bookedRooms.setText(String.valueOf(m.get("bookedRooms")));
            occupiedRooms.setText(String.valueOf(m.get("occupiedRooms")));
            totalCustomers.setText(String.valueOf(m.get("totalCustomers")));
            totalBookings.setText(String.valueOf(m.get("totalBookings")));
            totalStaff.setText(String.valueOf(m.get("totalStaff")));
            todayRevenue.setText("Rs. " +m.get("todayRevenue"));
            monthRevenue.setText("Rs. " +m.get("monthRevenue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
