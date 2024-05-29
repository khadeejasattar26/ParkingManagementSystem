package com.example.parkingmanagementsystem;

public class PricingTiers {
    private double hourlyRate;
    private double dailyRate;
    private double monthlyRate;

    public PricingTiers(double hourlyRate, double dailyRate, double monthlyRate) {
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.monthlyRate = monthlyRate;
    }

    // Getters and setters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }
}