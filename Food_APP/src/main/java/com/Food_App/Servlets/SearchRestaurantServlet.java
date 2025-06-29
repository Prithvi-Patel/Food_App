package com.Food_App.Servlets;

import com.Food_App.DAO.RestaurantDAO;
import com.Food_App.DAOimpl.RestaurantDAOimpl;
import com.Food_App.Models.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/SearchRestaurantServlet")
public class SearchRestaurantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String query = req.getParameter("query");

        RestaurantDAO dao = new RestaurantDAOimpl();
        List<Restaurant> matchedRestaurants = dao.searchRestaurantsByName(query);

        req.setAttribute("searchResults", matchedRestaurants);
        req.getRequestDispatcher("Search.jsp").forward(req, resp);
    }
}
