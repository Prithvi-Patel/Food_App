package com.Food_App.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.Food_App.DAOimpl.RestaurantDAOimpl;
import com.Food_App.Models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/restaurant")
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOimpl daoImpl=new RestaurantDAOimpl();
		List<Restaurant> allRestaurant=daoImpl.getActiveRestaurants();
		
		for(Restaurant restaurant: allRestaurant) {
			
			req.setAttribute("allRestaurant", allRestaurant);
			
			RequestDispatcher rd= req.getRequestDispatcher("Home.jsp");
			rd.forward(req,resp);
		}
	}
}
