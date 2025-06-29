package com.Food_App.DAO;

import java.util.List;
import com.Food_App.Models.Menu;

public interface MenuDAO {

    // Add a new menu item
    void addMenuItem(Menu menuItem);

    // Update existing menu item
    void updateMenuItem(Menu menuItem);

    // Delete menu item by its ID
    void deleteMenuItem(int menuId);

    // Retrieve a single menu item by ID
    Menu getMenuItemById(int menuId);

    // Retrieve all menu items
    List<Menu> getAllMenuItems();

    // Retrieve menu items for a specific restaurant
    List<Menu> getMenuItemsByRestaurantId(int restaurantId);

    // Retrieve available items for a restaurant (only those marked as available)
    List<Menu> getAvailableItemsByRestaurantId(int restaurantId);
}
