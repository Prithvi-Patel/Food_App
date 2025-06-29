package com.Food_App.DAOimpl;

import com.Food_App.DAO.CartDAO;
import com.Food_App.Models.Cart;
import com.Food_App.Models.CartItem;

import jakarta.servlet.http.HttpSession;

public class CartDAOImpl implements CartDAO {
    private final HttpSession session;

    public CartDAOImpl(HttpSession session) {
        this.session = session;
    }

    private Cart getCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public void addToCart(CartItem item) {
        getCart().addItem(item);
    }

    @Override
    public void updateQuantity(int menuId, int quantity) {
        getCart().updateQuantity(menuId, quantity);
    }

    @Override
    public void removeFromCart(int menuId) {
        getCart().removeItem(menuId);
    }
}
