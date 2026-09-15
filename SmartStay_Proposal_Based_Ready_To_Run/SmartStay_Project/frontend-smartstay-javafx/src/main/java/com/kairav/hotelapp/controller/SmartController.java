package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SmartController {
    @FXML private TextField budgetField;
    @FXML private ComboBox <String>categoryBox;
    @FXML private TextArea outputArea;
    @FXML public void initialize() {
        categoryBox.setItems(FXCollections.observableArrayList("Best Value","Family","Business","Luxury","Quiet","Group"));
        categoryBox.getSelectionModel().selectFirst();
    }
    @FXML public void loadPricing() {
        try {
            List <JsonNode>list =ApiClient.get("/smart/dynamic-pricing",new TypeReference <List <JsonNode>>() {});
            StringBuilder sb =new StringBuilder("Dynamic Pricing Result\n\n");
            for (JsonNode n:list) {
                sb.append("Room ").append(n.get("roomNumber").asText()).append(" (").append(n.get("roomType").asText()).append(")\n").append("Base Price: Rs. ").append(n.get("basePrice").asText()).append("\n").append("Occupancy: ").append(n.get("occupancyRate").asText()).append("%\n").append("Smart Price: Rs. ").append(n.get("smartPrice").asText()).append("\n").append(n.get("reason").asText()).append("\n\n");
            }
            outputArea.setText(sb.toString());
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void recommendRoom() {
        try {
            String budget =budgetField.getText().trim();
            if (budget.isEmpty()) {
                AlertUtil.error("Enter customer budget first");
                return;
            }
            String category =categoryBox.getValue() ==null ?"Best Value":categoryBox.getValue();
            String encodedCategory =URLEncoder.encode(category,StandardCharsets.UTF_8);
            JsonNode n =ApiClient.get("/smart/recommend-room?budget=" +budget +"&category=" +encodedCategory,new TypeReference <JsonNode>() {});
            outputArea.setText("Recommended Room\n\n" +"Category: " +n.get("category").asText() +"\n" +"Room: " +n.get("roomNumber").asText() +"\n" +"Type: " +n.get("roomType").asText() +"\n" +"Price: Rs. " +n.get("pricePerNight").asText() +"\n" +"Auto Upgrade: " +n.get("upgraded").asBoolean() +"\n" +"Message: " +n.get("message").asText());
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void forecastDemand() {
        try {
            JsonNode n =ApiClient.get("/smart/forecast-demand",new TypeReference <JsonNode>() {});
            outputArea.setText("Predictive Booking Forecast - Moving Average Method\n\n" +"Bookings in last 7 days: " +n.get("bookingsInLast7Days").asText() +"\n" +"Moving average per day: " +n.get("movingAveragePerDay").asText() +"\n" +"Predicted bookings next 7 days: " +n.get("predictedBookingsNext7Days").asText() +"\n" +"Recommendation: " +n.get("recommendation").asText());
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
}
