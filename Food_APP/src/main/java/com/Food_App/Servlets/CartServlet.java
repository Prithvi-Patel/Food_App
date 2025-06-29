package com.Food_App.Servlets;

import com.Food_App.DAO.*;
import com.Food_App.DAOimpl.*;
import com.Food_App.Models.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    // Helper method for quantity
	private int getQuantity1(Cart cartObj, int mId) {
        return cartObj.getAllItems().stream()
            .filter(i -> i.getMenuId() == mId)
            .findFirst()
            .map(CartItem::getQuantity)
            .orElse(0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        // Get request parameters
        String action = req.getParameter("action");
        String menuIdStr = req.getParameter("menuId");
        String restaurantIdStr = req.getParameter("restaurantId");

        // Validate input
        if (action == null || menuIdStr == null || restaurantIdStr == null) {
            resp.setContentType("text/html");
            resp.getWriter().println("<script>alert('Missing data: action, menuId or restaurantId'); window.history.back();</script>");
            return;
        }

        int menuId;
        int restaurantId;

        try {
            menuId = Integer.parseInt(menuIdStr);
            restaurantId = Integer.parseInt(restaurantIdStr);
        } catch (NumberFormatException e) {
            resp.setContentType("text/html");
            resp.getWriter().println("<script>alert('Invalid data format'); window.history.back();</script>");
            return;
        }

        // ✅ Do not check restaurant conflict for "remove", "increase", or "decrease"
        if (!action.equals("remove") && !action.equals("increase") && !action.equals("decrease")) {
            Integer sessionRestaurantId = (Integer) session.getAttribute("restaurantId");

            if (sessionRestaurantId != null && sessionRestaurantId != restaurantId) {
                resp.setContentType("text/html");
                resp.getWriter().println("<script>alert('You cannot order from multiple restaurants. Please order from the same restaurant.');window.history.back();</script>");
                return;
            }

            if (sessionRestaurantId == null) {
                session.setAttribute("restaurantId", restaurantId);  // Save restaurant in session only for adding
            }
        }

        MenuDAO menuDAO = new MenuDAOimpl();
        Menu menu = menuDAO.getMenuItemById(menuId);
        if (menu == null) {
            resp.getWriter().println("<script>alert('Item not found!');window.history.back();</script>");
            return;
        }

        CartDAO cartDAO = new CartDAOImpl(session);
        Cart cart = (Cart) session.getAttribute("cart");


        // 🔁 Action handling
        switch (action) {
            case "add":
                cartDAO.addToCart(new CartItem(menu.getMenuId(), restaurantId, menu.getItemName(), 1, menu.getPrice()));
                break;
            case "increase":
                cartDAO.updateQuantity(menuId, getQuantity(cart, menuId) + 1);
                break;
            case "decrease":
                int currentQty = getQuantity(cart, menuId);
                if (currentQty > 1) {
                    cartDAO.updateQuantity(menuId, currentQty - 1);
                } else {
                    cartDAO.removeFromCart(menuId);
                }
                break;
            case "remove":
                cartDAO.removeFromCart(menuId);
                break;
        }

        resp.sendRedirect("Cart.jsp");

    }

    private int getQuantity(Cart cart, int menuId) {
        if (cart == null || cart.getAllItems() == null) {
            return 0;
        }

        return cart.getAllItems().stream()
                .filter(item -> item.getMenuId() == menuId)
                .map(CartItem::getQuantity)
                .findFirst()
                .orElse(0);
    
	}
}