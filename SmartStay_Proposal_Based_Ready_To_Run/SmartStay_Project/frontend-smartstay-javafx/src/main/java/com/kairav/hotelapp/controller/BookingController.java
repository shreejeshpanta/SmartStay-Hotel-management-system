package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.*;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.util.*;

public class BookingController {
    @FXML private ComboBox <Customer>customerBox;
    @FXML private ComboBox <Room>roomBox;
    @FXML private DatePicker checkIn,checkOut;
    @FXML private TextField guests,advance;
    @FXML private TextArea specialRequest;
    @FXML private TableView <Booking>table;
    @FXML private TableColumn <Booking,Long>idCol;
    @FXML private TableColumn <Booking,String>customerCol,roomCol,statusCol;
    @FXML private TableColumn <Booking,Double>totalCol,remainingCol;
    private Booking selected;
    @FXML public void initialize() {
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        customerCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("customerName"));
        roomCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("roomNumber"));
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("bookingStatus"));
        totalCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("totalAmount"));
        remainingCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("remainingAmount"));
        table.getSelectionModel().selectedItemProperty().addListener((a,b,c) ->selected =c);
        loadBoxes();
        refresh();
    }
    private void loadBoxes() {
        try {
            customerBox.setItems(FXCollections.observableArrayList(ApiClient.get("/customers",new TypeReference <java.util.List <Customer>>() {})));
            roomBox.setItems(FXCollections.observableArrayList(ApiClient.get("/rooms/available",new TypeReference <java.util.List <Room>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void refresh() {
        try {
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/bookings",new TypeReference <java.util.List <Booking>>() {})));
            loadBoxes();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void book() {
        try {
            Map <String,Object>m =new HashMap<>();
            m.put("customerId",customerBox.getValue().getId());
            m.put("roomId",roomBox.getValue().getId());
            m.put("checkInDate",checkIn.getValue().toString());
            m.put("checkOutDate",checkOut.getValue().toString());
            m.put("numberOfGuests",Integer.parseInt(guests.getText()));
            m.put("advancePayment",advance.getText().isBlank() ?0:Double.parseDouble(advance.getText()));
            m.put("specialRequest",specialRequest.getText());
            ApiClient.post("/bookings",m,Booking.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void checkInAction() {
        if (selected ==null)return;
        try {
            ApiClient.put("/bookings/" +selected.getId() +"/check-in",null,Booking.class);
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void checkOutAction() {
        if (selected ==null)return;
        try {
            ApiClient.put("/bookings/" +selected.getId() +"/check-out",null,Booking.class);
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void cancel() {
        if (selected ==null)return;
        try {
            ApiClient.put("/bookings/" +selected.getId() +"/cancel",null,Booking.class);
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void clear() {
        customerBox.setValue(null);
        roomBox.setValue(null);
        checkIn.setValue(null);
        checkOut.setValue(null);
        guests.clear();
        advance.clear();
        specialRequest.clear();
    }
}
