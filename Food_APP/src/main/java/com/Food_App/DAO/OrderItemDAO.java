package com.Food_App.DAO;

import com.Food_App.Models.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    boolean saveOrderItems(List<OrderItem> orderItems, int orderId);
    List<OrderItem> getItemsByOrderId(int orderId);
	void addOrderItem(OrderItem orderItem);
}
