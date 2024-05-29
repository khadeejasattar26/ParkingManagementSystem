package com.example.parkingmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingManagementSystem {
    private List<ParkingLot> parkingLots;

    public ParkingManagementSystem() {
        this.parkingLots = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot lot) {
        parkingLots.add(lot);
    }

    public void removeParkingLot(ParkingLot lot) {
        parkingLots.remove(lot);
    }

    public ParkingLot findAvailableSpot(Vehicle vehicle) throws ParkingFullException {
        for (ParkingLot lot : parkingLots) {
            if (!lot.isFull()) {
                return lot;
            }
        }
        throw new ParkingFullException("All parking lots are full");
    }

    // Other management methods
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}