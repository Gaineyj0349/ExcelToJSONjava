package com.gainwise.iloveny.DataStructures;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("_id")
    private int id;

    @SerializedName("_name")
    private String name;

    @SerializedName("_type")
    private int type;

    public Ingredient(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
