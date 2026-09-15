package com.kairav.hotelapp.controller;

import com.kairav.hotelapp.MainApp;
import com.kairav.hotelapp.util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML private BorderPane rootPane;
    @FXML private Label userLabel;
    @FXML public void initialize() {
        userLabel.setText("Logged in: " +Session.fullName +" (" +Session.role +")");
        load("dashboard.fxml");
    }
    private void load(String f) {
        try {
            Parent p =FXMLLoader.load(getClass().getResource("/com/kairav/hotelapp/view/" +f));
            rootPane.setCenter(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML public void dashboard() {
        load("dashboard.fxml");
    }
    @FXML public void rooms() {
        load("rooms.fxml");
    }
    @FXML public void customers() {
        load("customers.fxml");
    }
    @FXML public void bookings() {
        load("bookings.fxml");
    }
    @FXML public void payments() {
        load("payments.fxml");
    }
    @FXML public void invoices() {
        load("invoice.fxml");
    }
    @FXML public void staff() {
        load("staff.fxml");
    }
    @FXML public void services() {
        load("services.fxml");
    }
    @FXML public void smart() {
        load("smart.fxml");
    }
    @FXML public void logout() throws Exception {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/kairav/hotelapp/view/login.fxml"));
        Scene scene =new Scene(loader.load(),1100,700);
        scene.getStylesheets().add(getClass().getResource("/com/kairav/hotelapp/css/style.css").toExternalForm());
        MainApp.primaryStage.setScene(scene);
    }
}
