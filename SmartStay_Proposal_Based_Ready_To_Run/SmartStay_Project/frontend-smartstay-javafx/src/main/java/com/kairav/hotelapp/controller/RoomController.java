package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.Room;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RoomController {
    @FXML private TextField roomNumber,roomType,bedType,capacity,floorNumber,price;
    @FXML private ComboBox <String>status;
    @FXML private TextArea description;
    @FXML private TableView <Room>table;
    @FXML private TableColumn <Room,Long>idCol;
    @FXML private TableColumn <Room,String>noCol,typeCol,statusCol;
    @FXML private TableColumn <Room,Double>priceCol;
    private Room selected;
    @FXML public void initialize() {
        status.setItems(FXCollections.observableArrayList("AVAILABLE","BOOKED","OCCUPIED","CLEANING","MAINTENANCE","OUT_OF_SERVICE"));
        status.setValue("AVAILABLE");
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        noCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("roomNumber"));
        typeCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("roomType"));
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        priceCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("pricePerNight"));
        table.getSelectionModel().selectedItemProperty().addListener((a,b,c) ->fill(c));
        refresh();
    }
    private Room form() {
        Room r =new Room();
        r.setRoomNumber(roomNumber.getText());
        r.setRoomType(roomType.getText());
        r.setBedType(bedType.getText());
        r.setCapacity(parseInt(capacity.getText()));
        r.setFloorNumber(parseInt(floorNumber.getText()));
        r.setPricePerNight(parseDouble(price.getText()));
        r.setStatus(status.getValue());
        r.setDescription(description.getText());
        return r;
    }
    private Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
    private Double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0.0;
        }
    }
    private void fill(Room r) {
        selected =r;
        if (r ==null)return;
        roomNumber.setText(r.getRoomNumber());
        roomType.setText(r.getRoomType());
        bedType.setText(r.getBedType());
        capacity.setText(String.valueOf(r.getCapacity()));
        floorNumber.setText(String.valueOf(r.getFloorNumber()));
        price.setText(String.valueOf(r.getPricePerNight()));
        status.setValue(r.getStatus());
        description.setText(r.getDescription());
    }
    @FXML public void refresh() {
        try {
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/rooms",new TypeReference <java.util.List <Room>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void add() {
        try {
            ApiClient.post("/rooms",form(),Room.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void update() {
        if (selected ==null)return;
        try {
            ApiClient.put("/rooms/" +selected.getId(),form(),Room.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void delete() {
        if (selected ==null)return;
        try {
            ApiClient.delete("/rooms/" +selected.getId());
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void clear() {
        selected =null;
        roomNumber.clear();
        roomType.clear();
        bedType.clear();
        capacity.clear();
        floorNumber.clear();
        price.clear();
        description.clear();
        status.setValue("AVAILABLE");
    }
}
