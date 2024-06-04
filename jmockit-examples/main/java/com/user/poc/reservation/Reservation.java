package com.user.poc.reservation;

public class Reservation {
    private int id;
    private int userId;
    private String details;
    private boolean isCancelled;

    public Reservation(int userId, String details) {
        this.userId = userId;
        this.details = details;
        this.isCancelled = false;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDetails() {
        return details;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}

