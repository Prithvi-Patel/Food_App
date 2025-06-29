package com.Food_App.Models;

public class CartItem {
    private int menuId;
    private int restaurantId;
    private String name;
    private int quantity;
    private double price;

    public CartItem(int menuId, int restaurantId, String name, int quantity, double price) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMenuId() { return menuId; }
    public int getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void incrementQuantity() { this.quantity++; }
    public double getTotalPrice() { return price * quantity; }
}
