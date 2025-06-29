package com.Food_App.Servlets;

import com.Food_App.DAO.OrderDAO;
import com.Food_App.DAO.OrderItemDAO;
import com.Food_App.DAOimpl.OrderDAOImpl;
import com.Food_App.DAOimpl.OrderItemDAOImpl;
import com.Food_App.Models.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Integer userId = (Integer) session.getAttribute("userId");

        String address = req.getParameter("address");
        String paymentMode = req.getParameter("paymentMode");

        
        if (userId == null) {
        	 session.setAttribute("redirectAfterLogin", "checkout"); // set redirect flag
            // Save checkout intent if needed (optional enhancement)
            resp.sendRedirect("Login.jsp");
            return;
        }

        if (cart == null || cart.getAllItems().isEmpty()) {
            resp.sendRedirect("Cart.jsp");
            return;
        }


        // Create and populate Order object
        Order order = new Order();
        order.setRestaurantId(cart.getAllItems().get(0).getRestaurantId());
        order.setUserId(userId);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(cart.getTotalAmount());
        order.setStatus("Pending");
        order.setPaymentMode(paymentMode);
        order.setAddress(address);

        // Create OrderItems from CartItems
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getAllItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuId(item.getMenuId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalAmount(item.getPrice() * item.getQuantity());
            orderItems.add(orderItem);
        }

        // Save Order and retrieve ID
        OrderDAO orderDAO = new OrderDAOImpl();
        int generatedOrderId = orderDAO.placeOrderAndReturnId(order);

        if (generatedOrderId > 0) {
            // Set orderId for each orderItem before saving
            for (OrderItem item : orderItems) {
                item.setOrderId(generatedOrderId);
            }

            // Use batch insert for OrderItems
            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
            boolean itemsSaved = orderItemDAO.saveOrderItems(orderItems, generatedOrderId);

            if (itemsSaved) {
                session.removeAttribute("cart");
                session.removeAttribute("restaurantId");
                resp.sendRedirect("OrderSuccess.jsp");
            } else {
                System.out.println("⚠️ OrderItems failed to save!");
                resp.sendRedirect("error.jsp");
            }

        } else {
            System.out.println("⚠️ Order failed to save!");
            resp.sendRedirect("error.jsp");
        }
    }
}
