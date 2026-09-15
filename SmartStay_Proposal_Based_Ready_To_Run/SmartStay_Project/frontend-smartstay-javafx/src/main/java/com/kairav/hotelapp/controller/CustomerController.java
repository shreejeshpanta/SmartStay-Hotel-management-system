package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.Customer;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CustomerController {
    @FXML private TextField firstName,lastName,gender,phone,email,address,nationality,idType,idNumber,emergencyContact;
    @FXML private TableView <Customer>table;
    @FXML private TableColumn <Customer,Long>idCol;
    @FXML private TableColumn <Customer,String>firstCol,lastCol,phoneCol,emailCol;
    private Customer selected;
    @FXML public void initialize() {
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        firstCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("firstName"));
        lastCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));
        phoneCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));
        table.getSelectionModel().selectedItemProperty().addListener((a,b,c) ->fill(c));
        refresh();
    }
    private Customer form() {
        Customer c =new Customer();
        c.setFirstName(firstName.getText());
        c.setLastName(lastName.getText());
        c.setGender(gender.getText());
        c.setPhone(phone.getText());
        c.setEmail(email.getText());
        c.setAddress(address.getText());
        c.setNationality(nationality.getText());
        c.setIdType(idType.getText());
        c.setIdNumber(idNumber.getText());
        c.setEmergencyContact(emergencyContact.getText());
        return c;
    }
    private void fill(Customer c) {
        selected =c;
        if (c ==null)return;
        firstName.setText(c.getFirstName());
        lastName.setText(c.getLastName());
        gender.setText(c.getGender());
        phone.setText(c.getPhone());
        email.setText(c.getEmail());
        address.setText(c.getAddress());
        nationality.setText(c.getNationality());
        idType.setText(c.getIdType());
        idNumber.setText(c.getIdNumber());
        emergencyContact.setText(c.getEmergencyContact());
    }
    @FXML public void refresh() {
        try {
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/customers",new TypeReference <java.util.List <Customer>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void add() {
        try {
            ApiClient.post("/customers",form(),Customer.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void update() {
        if (selected ==null)return;
        try {
            ApiClient.put("/customers/" +selected.getId(),form(),Customer.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void delete() {
        if (selected ==null)return;
        try {
            ApiClient.delete("/customers/" +selected.getId());
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
        nationality.clear();
        idType.clear();
        idNumber.clear();
        emergencyContact.clear();
    }
}
