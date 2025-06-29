package com.Food_App.Servlets;

import java.io.IOException;
import java.util.List;

import com.Food_App.DAOimpl.MenuDAOimpl;
import com.Food_App.DAOimpl.RestaurantDAOimpl;
import com.Food_App.Models.Menu;
import com.Food_App.Models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));

        MenuDAOimpl menuDao = new MenuDAOimpl();
        List<Menu> menuList = menuDao.getAvailableItemsByRestaurantId(restaurantId);
 
        RestaurantDAOimpl restaurantDao = new RestaurantDAOimpl();
        Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId); // ✅ Get name

        req.setAttribute("menuList", menuList);
        req.setAttribute("restaurantName", restaurant.getName()); // ✅ Pass name
        req.getSession().setAttribute("restaurantId", restaurant.getRestaurantId());
        req.getSession().setAttribute("restaurantName", restaurant.getName()); // ✅ Set name in session

        
        RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
        rd.forward(req, resp);
    }
}

