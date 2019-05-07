package com.gainwise.iloveny.DataStructures;

import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("_id")
    private int id;

    @SerializedName("_name")
    private String name;

    @SerializedName("_food_category")
    private int foodCategory;

    @SerializedName("_ingredients_array")
    private String ingredientsArray;

    @SerializedName("_price")
    private double price;

    @SerializedName("_description")
    private String Description;

    public Food(int id, String name, int foodCategory, String ingredientsArray, double price, String description) {
        this.id = id;
        this.name = name;
        this.foodCategory = foodCategory;
        this.ingredientsArray = ingredientsArray;
        this.price = price;
        Description = description;
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

    public int getfoodCategory() {
        return foodCategory;
    }

    public void setfoodCategory(int foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getIngredientsArray() {
        return ingredientsArray;
    }

    public void setIngredientsArray(String ingredientsArray) {
        this.ingredientsArray = ingredientsArray;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
