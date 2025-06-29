package com.Food_App.DAOimpl;

import com.Food_App.DAO.OrderDAO;
import com.Food_App.DBConnection.DBConnection;
import com.Food_App.Models.Menu;
import com.Food_App.Models.Order;
import com.Food_App.Models.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean placeOrder(Order order) {
        boolean success = false;
        String orderSql = "INSERT INTO orders (restaurant_id, user_id, total_amount, status, payment_mode, addres) VALUES (?, ?, ?, ?, ?, ?)";
        String itemSql = "INSERT INTO order_items (order_id, menu_id, item_name, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

            // Insert order
            orderStmt.setInt(1, order.getRestaurantId());
            orderStmt.setInt(2, order.getUserId());
            orderStmt.setDouble(3, order.getTotalAmount());
            orderStmt.setString(4, order.getStatus());
            orderStmt.setString(5, order.getPaymentMode());
            orderStmt.setString(6, order.getAddress());

            int affectedRows = orderStmt.executeUpdate();
            if (affectedRows == 0) return false;

            // Get generated order_id
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                // Save order items
                try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                    for (OrderItem item : order.getOrderitems()) {
                        itemStmt.setInt(1, orderId);
                        itemStmt.setInt(2, item.getMenuId());
                        itemStmt.setInt(4, item.getQuantity());
                        itemStmt.setDouble(5, item.getTotalAmount());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                    success = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setRestaurantId(rs.getInt("restaurant_id"));
                o.setUserId(rs.getInt("user_id"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setTotalAmount(rs.getDouble("total_amount"));
                o.setStatus(rs.getString("status"));
                o.setPaymentMode(rs.getString("payment_mode"));
                o.setAddress(rs.getString("addres"));
                orders.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order getOrdersByRestaurantId(int orderId) {
        Order o = null;
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setRestaurantId(rs.getInt("restaurant_id"));
                o.setUserId(rs.getInt("user_id"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setTotalAmount(rs.getDouble("total_amount"));
                o.setStatus(rs.getString("status"));
                o.setPaymentMode(rs.getString("payment_mode"));
                o.setAddress(rs.getString("addres"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return o;
    }
    public int placeOrderAndReturnId(Order order) {
        int orderId = -1;
        String sql = "INSERT INTO orders (restaurant_id, user_id, total_amount, status, payment_mode, addres) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getRestaurantId());
            ps.setInt(2, order.getUserId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getPaymentMode());
            ps.setString(6, order.getAddress());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1); // gets the auto-generated order_id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

}
