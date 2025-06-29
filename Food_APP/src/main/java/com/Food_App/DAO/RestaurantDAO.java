package com.Food_App.DAO;

import java.util.List;
import com.Food_App.Models.Restaurant;

public interface RestaurantDAO {

    // Add a new restaurant
    void addRestaurant(Restaurant restaurant);

    // Get a restaurant by its ID
    Restaurant getRestaurantById(int restaurantId);

    // Get all restaurants
    List<Restaurant> getAllRestaurants();

    // Update an existing restaurant
    void updateRestaurant(Restaurant restaurant);

    // Delete a restaurant by its ID
    void deleteRestaurant(int restaurantId);

    // Get all active restaurants
    List<Restaurant> getActiveRestaurants();

    // Get restaurants by cuisine type
    List<Restaurant> getRestaurantsByCuisine(String cuisineType);

    // Search restaurants by name (partial match)
    List<Restaurant> searchRestaurantsByName(String name);
    
    //getting the delivery time from the restaurant
    String getDeliveryTimeByRestaurantId(int restaurantId);

	List<Restaurant> searchRestaurantsByName1(String name);

}
