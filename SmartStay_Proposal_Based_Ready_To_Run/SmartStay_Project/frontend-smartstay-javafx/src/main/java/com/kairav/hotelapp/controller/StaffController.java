package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.Staff;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class StaffController {
    @FXML private TextField firstName,lastName,gender,phone,email,address,position,salary,shift;
    @FXML private ComboBox <String>status;
    @FXML private TableView <Staff>table;
    @FXML private TableColumn <Staff,Long>idCol;
    @FXML private TableColumn <Staff,String>firstCol,lastCol,positionCol,statusCol;
    private Staff selected;
    @FXML public void initialize() {
        status.setItems(FXCollections.observableArrayList("ACTIVE","INACTIVE","ON_LEAVE","RESIGNED"));
        status.setValue("ACTIVE");
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        firstCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("firstName"));
        lastCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));
        positionCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("position"));
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        table.getSelectionModel().selectedItemProperty().addListener((a,b,c) ->fill(c));
        refresh();
    }
    private Staff form() {
        Staff s =new Staff();
        s.setFirstName(firstName.getText());
        s.setLastName(lastName.getText());
        s.setGender(gender.getText());
        s.setPhone(phone.getText());
        s.setEmail(email.getText());
        s.setAddress(address.getText());
        s.setPosition(position.getText());
        try {
            s.setSalary(Double.parseDouble(salary.getText()));
        } catch (Exception e) {
            s.setSalary(0.0);
        }
        s.setShift(shift.getText());
        s.setStatus(status.getValue());
        return s;
    }
    private void fill(Staff s) {
        selected =s;
        if (s ==null)return;
        firstName.setText(s.getFirstName());
        lastName.setText(s.getLastName());
        gender.setText(s.getGender());
        phone.setText(s.getPhone());
        email.setText(s.getEmail());
        address.setText(s.getAddress());
        position.setText(s.getPosition());
        salary.setText(String.valueOf(s.getSalary()));
        shift.setText(s.getShift());
        status.setValue(s.getStatus());
    }
    @FXML public void refresh() {
        try {
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/staff",new TypeReference <java.util.List <Staff>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void add() {
        try {
            ApiClient.post("/staff",form(),Staff.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void update() {
        if (selected ==null)return;
        try {
            ApiClient.put("/staff/" +selected.getId(),form(),Staff.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void delete() {
        if (selected ==null)return;
        try {
            ApiClient.delete("/staff/" +selected.getId());
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void clear() {
        selected =null;
        firstName.clear();
        lastName.clear();
        gender.clear();
        phone.clear();
        email.clear();
        address.clear();
        position.clear();
        salary.clear();
        shift.clear();
        status.setValue("ACTIVE");
    }
}
