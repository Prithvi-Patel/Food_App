package com.Food_App.Models;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int menuId;
    private int quantity;
    private double totalAmount;
    
    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, double totalAmount) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}
	
	public OrderItem(int orderId, int menuId, int quantity, double totalAmount) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}
