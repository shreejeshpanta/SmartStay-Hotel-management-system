package com.kairav.hotelapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage primaryStage;
    @Override public void start(Stage stage) throws Exception {
        primaryStage =stage;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/kairav/hotelapp/view/login.fxml"));
        Scene scene =new Scene(loader.load(),1100,700);
        scene.getStylesheets().add(getClass().getResource("/com/kairav/hotelapp/css/style.css").toExternalForm());
        stage.setTitle("Hotel Management System");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
