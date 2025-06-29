package com.Food_App.DAOimpl;

import com.Food_App.DAO.OrderItemDAO;
import com.Food_App.Models.OrderItem;
import com.Food_App.DBConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean saveOrderItems(List<OrderItem> orderItems, int orderId) {
        boolean saved = true;
        String sql = "INSERT INTO order_items (order_id, menu_id, quantity, total_amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (OrderItem item : orderItems) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getMenuId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getTotalAmount());
                ps.addBatch();
            }

            // ⬇️ PLACE THIS BLOCK HERE
            int[] results = ps.executeBatch();
            for (int i = 0; i < results.length; i++) {
                if (results[i] == Statement.EXECUTE_FAILED) {
                    System.out.println("❌ Failed to insert order item at index " + i);
                    saved = false;
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            saved = false;
        }

        return saved;
    }


    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("menu_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalAmount(rs.getDouble("total_amount"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, menu_id, quantity, total_amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getTotalAmount());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
