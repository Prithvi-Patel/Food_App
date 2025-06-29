package com.Food_App.Models;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private String deliveryTime;
    private Integer adminUserId; // Can be null, so use Integer instead of int
    private Float rating;
    private boolean isActive;
    private String imagePath;

    // No-arg constructor
    public Restaurant() {
    }

    // Full-arg constructor
    public Restaurant(String name, String address, String phoneNumber, String cuisineType,
                      String deliveryTime, Integer adminUserId, Float rating, boolean isActive, String imagePath) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.isActive = isActive;
        this.imagePath = imagePath;
    }

    
    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
   public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisineType() {
        return cuisineType;
    }
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }
    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Restaurant :"+ restaurantId + name + address +  phoneNumber +  cuisineType + deliveryTime +
        		adminUserId + rating + isActive + imagePath ;
    }
}
