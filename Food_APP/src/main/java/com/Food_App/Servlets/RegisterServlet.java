package com.Food_App.Servlets;

import com.Food_App.DAO.UserDAO;
import com.Food_App.DAOimpl.UserDAOimpl;
import com.Food_App.Models.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Fetch form data
        String name = req.getParameter("name");
        String user_name = req.getParameter("user_name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phonenumber = req.getParameter("phonenumber");
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        // Create user object
        User user = new User();
        user.setName(name);
        user.setUsername(user_name);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhonenumber(phonenumber);
        user.setAddress(address);
        user.setRole(role);
        user.setCreated_Date(new Timestamp(System.currentTimeMillis()));
        user.setLast_Login_Date(new Timestamp(System.currentTimeMillis()));

        // Save user to DB
        UserDAO dao = new UserDAOimpl();
        dao.addUser(user);

        // Set session attributes
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("user_name", user.getUser_name());
        session.setAttribute("role", role);
   

        // Redirect based on role
        if ("customer".equalsIgnoreCase(role)) {
            resp.sendRedirect("Home.jsp");
        } else {
            resp.sendRedirect("Dashboard.jsp");
        }
    }
}
