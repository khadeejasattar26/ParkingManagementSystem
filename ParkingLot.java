package com.example.parkingmanagementsystem;

import java.util.ArrayList;

public class ParkingLot {
    private String id;
    private int capacity;
    private int availableSpots;
    private ArrayList<ParkingSpot> spots; // Change the declaration to ArrayList
    private PricingTiers pricingTiers;

    public ParkingLot(String id, int capacity, PricingTiers pricingTiers) {
        this.id = id;
        this.capacity = capacity;
        this.availableSpots = capacity;
        this.spots = new ArrayList<>(); // Change the initialization to ArrayList
        this.pricingTiers = pricingTiers;

        // Initialize parking spots with dynamic sizes
        for (int i = 0; i < capacity; i++) {
            VehicleType type = VehicleType.CAR; // Default to Car type
            if (i % 4 == 1) type = VehicleType.MOTORCYCLE;
            else if (i % 4 == 2) type = VehicleType.TRUCK;
            else if (i % 4 == 3) type = VehicleType.ELECTRIC_VEHICLE;

            spots.add(new ParkingSpot(type));
        }
    }

    public boolean addVehicle(Vehicle vehicle) throws ParkingFullException {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getType() == vehicle.getType()) {
                spot.occupy(vehicle); // Pass the vehicle to occupy method
                availableSpots--;
                return true;
            }
        }
        throw new ParkingFullException("Parking lot is full for vehicle type: " + vehicle.getType());
    }


    public boolean removeVehicle(Vehicle vehicle) throws VehicleNotFoundException {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.getType() == vehicle.getType()) {
                spot.vacate();
                availableSpots++;
                return true;
            }
        }
        throw new VehicleNotFoundException("Vehicle not found in parking lot");
    }

    public boolean isFull() {
        return availableSpots == 0;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public PricingTiers getPricingTiers() {
        return pricingTiers;
    }

    public ArrayList<ParkingSpot> getSpots() { // Change the return type to ArrayList
        return spots;
    }
}
