package com.Food_App.DAO;

import com.Food_App.Models.Order;
import java.util.List;

public interface OrderDAO {
    boolean placeOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
	Order getOrdersByRestaurantId(int orderId);
	int placeOrderAndReturnId(Order order);
}

