package com.example.parkingmanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {
    private ParkingManagementSystem pms;

    @Override
    public void start(Stage primaryStage) {
        pms = new ParkingManagementSystem();
        initializeTestData();

        BorderPane root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(createMainPane());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Parking Management System");
        primaryStage.show();
    }

    private void initializeTestData() {
        PricingTiers pricingTiers = new PricingTiers(10, 100, 500);
        ParkingLot lot1 = new ParkingLot("Lot1", 50, pricingTiers);
        ParkingLot lot2 = new ParkingLot("Lot2", 30, pricingTiers);
        pms.addParkingLot(lot1);
        pms.addParkingLot(lot2);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        menu.getItems().add(exitItem);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private Pane createMainPane() {
        VBox mainPane = new VBox();
        mainPane.getChildren().add(createParkingLotStatusPane());
        mainPane.getChildren().add(createVehicleEntryExitPane());
        return mainPane;
    }

    private Pane createParkingLotStatusPane() {
        VBox parkingLotStatusPane = new VBox();
        Label title = new Label("Parking Lot Status");

        for (ParkingLot lot : pms.getParkingLots()) {
            Label status = new Label("Parking Lot " + lot.getId() + ": " + lot.getAvailableSpots() + " spots available");
            parkingLotStatusPane.getChildren().add(status);
        }

        return parkingLotStatusPane;
    }

    private Pane createVehicleEntryExitPane() {
        VBox vehicleEntryExitPane = new VBox();
        Label title = new Label("Vehicle Entry/Exit");

        TextField licensePlateField = new TextField();
        licensePlateField.setPromptText("Enter License Plate Number");

        ComboBox<VehicleType> vehicleTypeBox = new ComboBox<>();
        vehicleTypeBox.getItems().addAll(VehicleType.values());

        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> {
            String licensePlate = licensePlateField.getText();
            VehicleType type = vehicleTypeBox.getValue();
            if (licensePlate.isEmpty() || type == null) {
                showAlert("Error", "License Plate and Vehicle Type must be provided");
                return;
            }

            Vehicle vehicle = null;
            switch (type) {
                case CAR:
                    vehicle = new Car(licensePlate);
                    break;
                case MOTORCYCLE:
                    vehicle = new Motorcycle(licensePlate);
                    break;
                case TRUCK:
                    vehicle = new Truck(licensePlate);
                    break;
                case ELECTRIC_VEHICLE:
                    vehicle = new EV(licensePlate);
                    break;
            }

            if (vehicle != null) {
                try {
                    ParkingLot lot = pms.findAvailableSpot(vehicle);
                    lot.addVehicle(vehicle);
                    showAlert("Success", "Vehicle entered the parking lot: " + lot.getId());
                } catch (ParkingFullException ex) {
                    showAlert("Error", ex.getMessage());
                }
            } else {
                showAlert("Error", "Invalid vehicle type selected");
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            String licensePlate = licensePlateField.getText();
            if (licensePlate.isEmpty()) {
                showAlert("Error", "License Plate must be provided");
                return;
            }

            for (ParkingLot lot : pms.getParkingLots()) {
                try {
                    for (ParkingSpot spot : lot.getSpots()) {
                        if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlateNumber().equals(licensePlate)) {
                            lot.removeVehicle(spot.getParkedVehicle());
                            showAlert("Success", "Vehicle exited the parking lot: " + lot.getId());
                            return;
                        }
                    }
                } catch (VehicleNotFoundException ex) {
                    showAlert("Error", ex.getMessage());
                }
            }

            showAlert("Error", "Vehicle not found in any parking lot");
        });

        vehicleEntryExitPane.getChildren().addAll(title, licensePlateField, vehicleTypeBox, enterButton, exitButton);
        return vehicleEntryExitPane;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
