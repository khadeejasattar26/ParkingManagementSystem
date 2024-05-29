package com.example.parkingmanagementsystem;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleType type;
    private boolean isOccupied;

    public ParkingSpot(VehicleType type) {
        this.type = type;
        this.isOccupied = false;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
}
