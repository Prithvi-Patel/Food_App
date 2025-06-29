package com.Food_App.DAOimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Food_App.DAO.MenuDAO;
import com.Food_App.DBConnection.DBConnection;
import com.Food_App.Models.Menu;

public class MenuDAOimpl implements MenuDAO {

    private final String INSERT = "INSERT INTO menu (restaurant_id, item_name, description, price, is_available, image_path) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE menu SET restaurant_id = ?, item_name = ?, description = ?, price = ?, is_available = ?, image_path = ? WHERE menu_id = ?";
    private final String DELETE = "DELETE FROM menu WHERE menu_id = ?";
    private final String GET_BY_ID = "SELECT * FROM menu WHERE menu_id = ?";
    private final String GET_ALL = "SELECT * FROM menu";
    private final String GET_BY_RESTAURANT = "SELECT * FROM menu WHERE restaurant_id = ?";
    private final String GET_AVAILABLE_BY_RESTAURANT = "SELECT * FROM menu WHERE restaurant_id = ? AND is_available = 1";

    @Override
    public void addMenuItem(Menu menuItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {

            ps.setInt(1, menuItem.getRestaurantId());
            ps.setString(2, menuItem.getItemName());
            ps.setString(3, menuItem.getDescription());
            ps.setDouble(4, menuItem.getPrice());
            ps.setBoolean(5, menuItem.isAvailability());
            ps.setString(6, menuItem.getImagePath());

            ps.executeUpdate();
            System.out.println("Menu item added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMenuItem(Menu menuItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {

            ps.setInt(1, menuItem.getRestaurantId());
            ps.setString(2, menuItem.getItemName());
            ps.setString(3, menuItem.getDescription());
            ps.setDouble(4, menuItem.getPrice());
            ps.setBoolean(5, menuItem.isAvailability());
            ps.setString(6, menuItem.getImagePath());
            ps.setInt(7, menuItem.getMenuId());

            ps.executeUpdate();
            System.out.println("Menu item updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenuItem(int menuId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {

            ps.setInt(1, menuId);
            ps.executeUpdate();
            System.out.println("Menu item deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenuItemById(int menuId) {
        Menu menu = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {

            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                menu = extractMenuFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public List<Menu> getAllMenuItems() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menuList.add(extractMenuFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public List<Menu> getMenuItemsByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_RESTAURANT)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menuList.add(extractMenuFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public List<Menu> getAvailableItemsByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_AVAILABLE_BY_RESTAURANT)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menuList.add(extractMenuFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    private Menu extractMenuFromResultSet(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setMenuId(rs.getInt("menu_id"));
        menu.setRestaurantId(rs.getInt("restaurant_id"));
        menu.setItemName(rs.getString("item_name"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setAvailability(rs.getBoolean("is_available"));
        menu.setImagePath(rs.getString("image_path"));
        return menu;
    }
}
