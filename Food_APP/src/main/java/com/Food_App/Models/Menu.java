package com.Food_App.Models;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private double price;
    private boolean availability;
    private String imagePath;
    private String category;

    public Menu() {
        // No-arg constructor
    }

    // Constructor without menuId (used when inserting new records)
    public Menu(int restaurantId, String itemName, String description, double price, boolean availability, String imagePath, String category) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.imagePath = imagePath;
        this.category = category;
    }

    // Constructor with menuId (used when fetching from DB)
    public Menu(int menuId, int restaurantId, String itemName, String description, double price, boolean availability, String imagePath, String category) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.imagePath = imagePath;
        this.category = category;
    }

    // Getters and Setters

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Menu :"+ menuId + restaurantId + itemName +description +price + availability + imagePath + category ;
    }
}
