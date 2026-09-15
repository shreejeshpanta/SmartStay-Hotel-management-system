package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.HotelService;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class ServiceController {
    @FXML private TextField serviceName,price;
    @FXML private TextArea description;
    @FXML private CheckBox active;
    @FXML private TableView <HotelService>table;
    @FXML private TableColumn <HotelService,Long>idCol;
    @FXML private TableColumn <HotelService,String>nameCol;
    @FXML private TableColumn <HotelService,Double>priceCol;
    private HotelService selected;
    @FXML public void initialize() {
        active.setSelected(true);
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("serviceName"));
        priceCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        table.getSelectionModel().selectedItemProperty().addListener((a,b,c) ->fill(c));
        refresh();
    }
    private HotelService form() {
        HotelService s =new HotelService();
        s.setServiceName(serviceName.getText());
        try {
            s.setPrice(Double.parseDouble(price.getText()));
        } catch (Exception e) {
            s.setPrice(0.0);
        }
        s.setDescription(description.getText());
        s.setActive(active.isSelected());
        return s;
    }
    private void fill(HotelService s) {
        selected =s;
        if (s ==null)return;
        serviceName.setText(s.getServiceName());
        price.setText(String.valueOf(s.getPrice()));
        description.setText(s.getDescription());
        active.setSelected(Boolean.TRUE.equals(s.getActive()));
    }
    @FXML public void refresh() {
        try {
            table.setItems(FXCollections.observableArrayList(ApiClient.get("/services",new TypeReference <java.util.List <HotelService>>() {})));
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void add() {
        try {
            ApiClient.post("/services",form(),HotelService.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void update() {
        if (selected ==null)return;
        try {
            ApiClient.put("/services/" +selected.getId(),form(),HotelService.class);
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void delete() {
        if (selected ==null)return;
        try {
            ApiClient.delete("/services/" +selected.getId());
            clear();
            refresh();
        } catch (Exception e) {
            AlertUtil.error(e.getMessage());
        }
    }
    @FXML public void clear() {
        selected =null;
        serviceName.clear();
        price.clear();
        description.clear();
        active.setSelected(true);
    }
}
