package com.user.poc.order;

public class Order {
    private int id;
    private String description;

    public Order(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

