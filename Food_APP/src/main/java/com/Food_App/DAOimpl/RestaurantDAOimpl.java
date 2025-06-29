package com.Food_App.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Food_App.DAO.RestaurantDAO;
import com.Food_App.DBConnection.DBConnection;
import com.Food_App.Models.Restaurant;

public class RestaurantDAOimpl implements RestaurantDAO {

    private final String INSERT = "INSERT INTO restaurant (name, address, phone_number, cuisine_type, delivery_time, admin_user_id, rating, is_active, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE restaurant SET name=?, address=?, phone_number=?, cuisine_type=?, delivery_time=?, admin_user_id=?, rating=?, is_active=?, image_path=? WHERE restaurant_id=?";
    private final String DELETE = "DELETE FROM restaurant WHERE restaurant_id=?";
    private final String GET_BY_ID = "SELECT * FROM restaurant WHERE restaurant_id=?";
    private final String GET_ALL = "SELECT * FROM restaurant";
    private final String GET_ACTIVE = "SELECT * FROM restaurant WHERE is_active=1";
    private final String GET_BY_CUISINE = "SELECT * FROM restaurant WHERE cuisine_type=?";
    private final String SEARCH_BY_NAME = "SELECT * FROM restaurant WHERE name LIKE ?";

    @Override
    public void addRestaurant(Restaurant r) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getPhoneNumber());
            ps.setString(4, r.getCuisineType());
            ps.setString(5, r.getDeliveryTime());
            ps.setInt(6, r.getAdminUserId());
            ps.setFloat(7, r.getRating());
            ps.setBoolean(8, r.isActive());
            ps.setString(9, r.getImagePath());
            
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        Restaurant r = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = mapRestaurant(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateRestaurant(Restaurant r) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getPhoneNumber());
            ps.setString(4, r.getCuisineType());
            ps.setString(5, r.getDeliveryTime());
            ps.setInt(6, r.getAdminUserId());
            ps.setFloat(7, r.getRating());
            ps.setBoolean(8, r.isActive());
            ps.setString(9, r.getImagePath());
            ps.setInt(10, r.getRestaurantId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getActiveRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ACTIVE)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Restaurant> getRestaurantsByCuisine(String cuisineType) {
        List<Restaurant> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_CUISINE)) {

            ps.setString(1, cuisineType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Restaurant> searchRestaurantsByName(String name) {
        List<Restaurant> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_BY_NAME)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Restaurant mapRestaurant(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));  // ✅ CRITICAL LINE
        restaurant.setName(rs.getString("name"));
        restaurant.setAddress(rs.getString("address"));
        restaurant.setPhoneNumber(rs.getString("phone_number"));
        restaurant.setCuisineType(rs.getString("cuisine_type"));
        restaurant.setDeliveryTime(rs.getString("delivery_time"));
        restaurant.setAdminUserId(rs.getInt("admin_user_id"));
        restaurant.setRating(rs.getFloat("rating"));
        restaurant.setActive(rs.getBoolean("is_active"));
        restaurant.setImagePath(rs.getString("image_path"));
    
        return restaurant;
    }

	@Override
	public String getDeliveryTimeByRestaurantId(int restaurantId) {
	    String deliveryTime = null;
	    String sql = "SELECT delivery_time FROM restaurant WHERE restaurant_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, restaurantId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            deliveryTime = rs.getString("delivery_time");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return deliveryTime;
	}
	
	@Override
	public List<Restaurant> searchRestaurantsByName1(String name) {
	    List<Restaurant> list = new ArrayList<>();
	    String sql = "SELECT * FROM restaurant WHERE name LIKE ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, "%" + name + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Restaurant restaurant = new Restaurant();
	            restaurant.setRestaurantId(rs.getInt("restaurant_id"));
	            restaurant.setName(rs.getString("name"));
	            restaurant.setCuisineType(rs.getString("cuisine_type"));
	            restaurant.setRating(rs.getFloat("rating"));
	            restaurant.setImagePath(rs.getString("image_path"));
	            list.add(restaurant);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}


}
