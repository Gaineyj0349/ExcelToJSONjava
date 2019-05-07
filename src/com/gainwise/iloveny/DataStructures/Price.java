package com.gainwise.iloveny.DataStructures;

import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("_id")
    private int id;

    @SerializedName("_amount")
    private double amount;


    public Price(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
