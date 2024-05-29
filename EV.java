package com.example.parkingmanagementsystem;

public class EV extends Vehicle {
    public EV(String licensePlate) {
        super(licensePlate, VehicleType.ELECTRIC_VEHICLE);
    }
}
