package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.*;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.util.*;

public class PaymentController {
    @FXML private ComboBox <Booking>bookingBox;
    @FXML private ComboBox <String>methodBox;
    @FXML private TextField paidAmount;
    @FXML private TableView <Payment>table;
    @FXML private TableColumn <Payment,Long>idCol;
    @FXML private TableColumn <Payment,String>methodCol,statusCol;
    @FXML private TableColumn <Payment,Double>totalCol,paidCol,remainingCol;
    @FXML public void initialize() {
        methodBox.setItems(FXCollections.observableArrayList("CASH","CARD","ONLINE","BANK_TRANSFER","ESEWA","KHALTI","CONNECTIPS"));
        methodBox.setValue("CASH");
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        methodCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("paymentMethod"));
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("paymentStatus"));
        totalCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("totalAmount"));
        paidCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("paidAmount"));
        remainingCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("remainingAmount"));
        refresh();
    }
    @FXML public void refresh() {
        try {
            bookingBox.setItems(FXCollections.observableArrayList(ApiClient.get("/bookings",new TypeReference <java.util.List <Booking>>() {})));
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/payments",new TypeReference <java.util.List <Payment>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void pay() {
        try {
            Map <String,Object>m =new HashMap<>();
            m.put("bookingId",bookingBox.getValue().getId());
            m.put("paymentMethod",methodBox.getValue());
            m.put("paidAmount",Double.parseDouble(paidAmount.getText()));
            ApiClient.post("/payments",m,Payment.class);
            paidAmount.clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
}
