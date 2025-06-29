package com.Food_App.Models;

import java.util.*;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();

    public void addItem(CartItem item) {
        items.merge(item.getMenuId(), item, (existing, newItem) -> {
            existing.incrementQuantity();
            return existing;
        });
    }

    public void updateQuantity(int menuId, int quantity) {
        if (quantity <= 0) {
            items.remove(menuId);
        } else if (items.containsKey(menuId)) {
            items.get(menuId).setQuantity(quantity);
        }
    }

    public void removeItem(int menuId) {
        items.remove(menuId);
    }

    public List<CartItem> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public double getTotalAmount() {
        return items.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
