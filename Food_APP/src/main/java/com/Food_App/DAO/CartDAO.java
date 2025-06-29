package com.Food_App.DAO;

import com.Food_App.Models.CartItem;

public interface CartDAO {
    void addToCart(CartItem item);
    void updateQuantity(int menuId, int quantity);
    void removeFromCart(int menuId);
}
