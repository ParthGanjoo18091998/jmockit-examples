package com.user.poc.payement;

public class Payment {
    private int userId;
    private double amount;

    public Payment(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}

