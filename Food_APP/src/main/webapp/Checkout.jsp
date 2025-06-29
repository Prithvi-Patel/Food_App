<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Food_App.Models.Cart" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.Food_App.DAO.RestaurantDAO, com.Food_App.DAOimpl.RestaurantDAOimpl" %>
<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null || cart.getAllItems().isEmpty()) {
        response.sendRedirect("Cart.jsp");
        return;
    }
    DecimalFormat df = new DecimalFormat("0.00");
    Integer restId = (Integer) session.getAttribute("restaurantId");
    RestaurantDAO restaurantDAO = new RestaurantDAOimpl();
    String deliveryTime = restaurantDAO.getDeliveryTimeByRestaurantId(restId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #fdfbfb, #ebedee);
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 700px;
            margin: 50px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        label {
            font-weight: bold;
            color: #444;
            display: block;
            margin-top: 20px;
        }

        textarea, select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }

        .delivery-section {
            margin-top: 25px;
        }

        .delivery-status {
            background-color: #f8f8f8;
            border-left: 5px solid #4caf50;
            padding: 15px 20px;
            margin-top: 15px;
            border-radius: 8px;
        }

        .delivery-header {
            display: flex;
            align-items: center;
        }

        .delivery-icon {
            width: 28px;
            height: 28px;
            margin-right: 10px;
        }

        .progress-info {
            display: flex;
            justify-content: space-between;
            font-size: 14px;
            color: #555;
            margin-bottom: 8px;
        }

        .progress-track {
            background-color: #ddd;
            border-radius: 20px;
            height: 16px;
            overflow: hidden;
        }

        .progress-fill {
            height: 100%;
            background: linear-gradient(to right, #66bb6a, #43a047);
            width: 75%;
            transition: width 0.6s ease;
        }

        .total {
            margin-top: 25px;
            font-size: 18px;
            font-weight: bold;
            text-align: right;
            color: #333;
        }

        .btn-submit {
            margin-top: 30px;
            width: 100%;
            padding: 15px;
            background-color: #ff4d4d;
            color: white;
            font-size: 17px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn-submit:hover {
            background-color: #e03a3a;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px;
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Checkout - Delivery & Payment</h2>
 	<form action="Checkout" method="post">

        <label for="address">Delivery Address:</label>
        <textarea id="address" name="address" rows="1" required placeholder="Enter your full delivery address..."></textarea>

        <label for="payment">Payment Method:</label>
        <select id="payment" name="paymentMode" required>
            <option value="">--Select Payment Method--</option>
            <option value="Cash on Delivery">Cash on Delivery</option>
            <option value="UPI / QR Code">UPI / QR Code</option>
            <option value="Credit/Debit Card">Credit/Debit Card</option>
            <option value="Net Banking">Net Banking</option>
        </select>

        <div class="delivery-section">
            <div class="delivery-status">
                <div class="delivery-header">
                    <img src="https://cdn-icons-png.flaticon.com/512/3081/3081648.png" class="delivery-icon" alt="Delivery Icon">
                    <span><strong>Estimated Delivery:</strong> <%= deliveryTime %></span>
                </div>
            </div>

            <!-- Alternate Progress Bar -->
            <div class="progress-bar-alt">
                <div class="progress-info">
                </div>              
            </div>
        </div>

        <div class="total">
            Total: ₹<%= df.format(cart.getTotalAmount()) %>
        </div>
		
        <button type="submit" class="btn-submit">Place Order</button>
    </form>
</div>

</body>
</html>
