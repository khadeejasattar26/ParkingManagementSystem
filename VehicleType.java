package com.example.parkingmanagementsystem;



public enum VehicleType {
    CAR(1),
    MOTORCYCLE(1),
    TRUCK(5),
    ELECTRIC_VEHICLE(1);

    private final int slots;

    VehicleType(int slots) {
        this.slots = slots;
    }

    public int getSlots() {
        return slots;
    }
}
