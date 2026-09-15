package com.kairav.hotelapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kairav.hotelapp.api.ApiClient;
import com.kairav.hotelapp.model.Booking;
import com.kairav.hotelapp.model.Customer;
import com.kairav.hotelapp.model.Invoice;
import com.kairav.hotelapp.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.List;

public class InvoiceController {
    @FXML private ComboBox <Customer>customerBox;
    @FXML private ComboBox <Booking>bookingBox;
    @FXML private TextField roomCharge;
    @FXML private TextField serviceCharge;
    @FXML private TextField taxAmount;
    @FXML private TableView <Invoice>table;
    @FXML private TableColumn <Invoice,Long>idCol;
    @FXML private TableColumn <Invoice,String>customerCol;
    @FXML private TableColumn <Invoice,String>bookingCol;
    @FXML private TableColumn <Invoice,Double>roomCol;
    @FXML private TableColumn <Invoice,Double>serviceCol;
    @FXML private TableColumn <Invoice,Double>taxCol;
    @FXML private TableColumn <Invoice,Double>totalCol;
    private final ObservableList <Invoice>invoices =FXCollections.observableArrayList();
    private List <Booking>bookings =List.of();
    private long nextInvoiceId =1;
    @FXML public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        bookingCol.setCellValueFactory(new PropertyValueFactory<>("bookingSummary"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomCharge"));
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("serviceCharge"));
        taxCol.setCellValueFactory(new PropertyValueFactory<>("taxAmount"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        table.setItems(invoices);
        customerBox.valueProperty().addListener((obs,oldValue,customer) ->filterBookings(customer));
        bookingBox.valueProperty().addListener((obs,oldValue,booking) ->fillRoomCharge(booking));
        refresh();
    }
    @FXML public void refresh() {
        try {
            customerBox.setItems(FXCollections.observableArrayList(ApiClient.get("/customers",new TypeReference <List <Customer>>() {})));
            bookings =ApiClient.get("/bookings",new TypeReference <List <Booking>>() {});
            filterBookings(customerBox.getValue());
        } catch (Exception e) {
            AlertUtil.error("Unable to load invoice data: " +e.getMessage());
        }
    }
    @FXML public void generateInvoice() {
        try {
            Customer customer =customerBox.getValue();
            Booking booking =bookingBox.getValue();
            if (customer ==null ||booking ==null) {
                AlertUtil.error("Please select a customer and booking.");
                return;
            }
            double room =parseAmount(roomCharge,"room charge");
            double service =parseOptionalAmount(serviceCharge);
            double tax =parseOptionalAmount(taxAmount);
            double total =room +service +tax;
            Invoice invoice =new Invoice();
            invoice.setId(nextInvoiceId ++);
            invoice.setCustomer(customer);
            invoice.setBooking(booking);
            invoice.setRoomCharge(room);
            invoice.setServiceCharge(service);
            invoice.setTaxAmount(tax);
            invoice.setTotalAmount(total);
            invoice.setPaidAmount(0.0);
            invoice.setRemainingAmount(total);
            invoice.setInvoiceDate(LocalDate.now().toString());
            invoice.setPaymentStatus("UNPAID");
            invoices.add(0,invoice);
            table.getSelectionModel().select(invoice);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Invoice Generated");
            alert.setContentText("Total Bill Amount: Rs. " +total);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML public void printBill() {
        Invoice invoice =table.getSelectionModel().getSelectedItem();
        if (invoice ==null && !invoices.isEmpty()) {
            invoice =invoices.get(0);
        }
        if (invoice ==null) {
            AlertUtil.error("Please generate or select an invoice first.");
            return;
        }
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Print");
        alert.setContentText("Invoice #" +invoice.getId() +"\n" +"Customer: " +invoice.getCustomerName() +"\n" +"Booking: " +invoice.getBookingSummary() +"\n" +"Room Charge: Rs. " +invoice.getRoomCharge() +"\n" +"Service Charge: Rs. " +invoice.getServiceCharge() +"\n" +"Tax: Rs. " +invoice.getTaxAmount() +"\n" +"Total: Rs. " +invoice.getTotalAmount() +"\n\n" +"Bill sent to printer.");
        alert.showAndWait();
    }
    private void filterBookings(Customer customer) {
        if (customer ==null) {
            bookingBox.setItems(FXCollections.observableArrayList(bookings));
            return;
        }
        bookingBox.setItems(FXCollections.observableArrayList(bookings.stream().filter(booking ->booking.getCustomer() !=null &&customer.getId().equals(booking.getCustomer().getId())).toList()));
        bookingBox.setValue(null);
    }
    private void fillRoomCharge(Booking booking) {
        if (booking ==null ||booking.getRemainingAmount() ==null) {
            return;
        }
        roomCharge.setText(String.valueOf(booking.getRemainingAmount()));
        if (serviceCharge.getText().isBlank()) {
            serviceCharge.setText("0");
        }
        if (taxAmount.getText().isBlank()) {
            taxAmount.setText("0");
        }
    }
    private double parseAmount(TextField field,String label) {
        String value =field.getText() ==null ?"":field.getText().trim();
        if (value.isBlank()) {
            throw new IllegalArgumentException("Please enter " +label +".");
        }
        double amount =Double.parseDouble(value);
        if (amount <0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return amount;
    }
    private double parseOptionalAmount(TextField field) {
        String value =field.getText() ==null ?"":field.getText().trim();
        if (value.isBlank()) {
            return 0.0;
        }
        double amount =Double.parseDouble(value);
        if (amount <0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return amount;
    }
}
