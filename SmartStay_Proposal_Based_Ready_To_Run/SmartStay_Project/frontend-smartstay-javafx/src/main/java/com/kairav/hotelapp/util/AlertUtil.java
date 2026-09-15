package com.kairav.hotelapp.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void info(String m) {
        new Alert(Alert.AlertType.INFORMATION,m).showAndWait();
    }
    public static void error(String m) {
        new Alert(Alert.AlertType.ERROR,m).showAndWait();
    }
}
