package com.kairav.hotelapp.controller;

import com.kairav.hotelapp.MainApp;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.LoginResponse;
import com.kairav.hotelapp.util.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.util.*;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML public void login() {
        try {
            Map <String,String>body =new HashMap<>();
            body.put("username",usernameField.getText());
            body.put("password",passwordField.getText());
            LoginResponse r =ApiClient.post("/auth/login",body,LoginResponse.class);
            Session.username =r.getUsername();
            Session.fullName =r.getFullName();
            Session.role =r.getRole();
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/kairav/hotelapp/view/main.fxml"));
            Scene scene =new Scene(loader.load(),1100,700);
            scene.getStylesheets().add(getClass().getResource("/com/kairav/hotelapp/css/style.css").toExternalForm());
            MainApp.primaryStage.setScene(scene);
        } catch (Exception e) {
            AlertUtil.error("Login failed. Make sure backend is running.\n" +e.getMessage());
        }
    }
}
