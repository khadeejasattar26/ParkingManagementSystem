package com.example.parkingmanagementsystem;

public class Vehicle {
    private String licensePlateNumber;
    private VehicleType type;
    private long entryTimestamp;

    public Vehicle(String licensePlateNumber, VehicleType type) {
        this.licensePlateNumber = licensePlateNumber;
        this.type = type;
        this.entryTimestamp = System.currentTimeMillis();
    }

    // Getters and setters
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }
}