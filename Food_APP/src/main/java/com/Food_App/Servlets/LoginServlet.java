package com.Food_App.Servlets;

import com.Food_App.DAO.RestaurantDAO;
import com.Food_App.DAO.UserDAO;
import com.Food_App.DAOimpl.RestaurantDAOimpl;
import com.Food_App.DAOimpl.UserDAOimpl;
import com.Food_App.Models.Restaurant;
import com.Food_App.Models.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final int MAX_ATTEMPTS = 3;
    private static final long BLOCK_TIME = 5 * 60 * 1000; // 5 minutes

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("user_name");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        // Check login attempts
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        Long blockedUntil = (Long) session.getAttribute("blockedUntil");

        if (attempts == null) attempts = 0;

        if (blockedUntil != null && System.currentTimeMillis() < blockedUntil) {
            long secondsLeft = (blockedUntil - System.currentTimeMillis()) / 1000;
            response.sendRedirect("Login.jsp?error=Account locked. Try again in " + secondsLeft + " seconds.");
            return;
        }

        UserDAO userDao = new UserDAOimpl();
        RestaurantDAO restaurantDao = new RestaurantDAOimpl();
        List<User> users = userDao.getAllUsers();

        for (User user : users) {
            if (user.getUser_name().equals(username) && user.getPassword().equals(password)) {
                // ✅ Successful login
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("user_name", user.getUser_name());
                session.setAttribute("role", user.getRole());

                // Load restaurants
                List<Restaurant> restaurants = restaurantDao.getAllRestaurants();
                session.setAttribute("allRestaurant", restaurants);

                // Reset attempt tracking
                session.removeAttribute("loginAttempts");
                session.removeAttribute("blockedUntil");

                // Redirect based on session
                String redirectTo = (String) session.getAttribute("redirectAfterLogin");
                if ("checkout".equals(redirectTo)) {
                    session.removeAttribute("redirectAfterLogin");
                    response.sendRedirect("Checkout.jsp");
                } else {
                    response.sendRedirect("Home.jsp");
                }
                return;
            }
        }

        // ❌ Failed login
        attempts++;
        session.setAttribute("loginAttempts", attempts);

        if (attempts >= MAX_ATTEMPTS) {
            session.setAttribute("blockedUntil", System.currentTimeMillis() + BLOCK_TIME);
            response.sendRedirect("Login.jsp?error=Too many failed attempts. Try again in 5 minutes.");
        } else {
            int remaining = MAX_ATTEMPTS - attempts;
            response.sendRedirect("Login.jsp?error=Invalid credentials. " + remaining + " attempt(s) left.");
        }
    }
}
