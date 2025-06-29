<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Food_App.Models.CartItem, com.Food_App.Models.Cart" %>
<%@ page import="java.util.List" %>
<%
    Cart cart = (Cart) session.getAttribute("cart");
    List<CartItem> items = (cart != null) ? cart.getAllItems() : new java.util.ArrayList<>();
    double total = (cart != null) ? cart.getTotalAmount() : 0.0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f5f5;
            padding: 40px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .cart-container {
            max-width: 1000px;
            margin: 30px auto;
            background: #fff;
            border-radius: 8px;
            padding: 25px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 14px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #ff4d4d;
            color: white;
        }
        .btn {
            padding: 6px 12px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            margin: 0 2px;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-remove {
            background-color: #dc3545;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .empty-msg {
            text-align: center;
            padding: 50px;
            color: #888;
            font-size: 18px;
        }
        .total {
            text-align: right;
            font-size: 20px;
            margin-top: 20px;
            color: #333;
        }
        .checkout-btn {
            display: inline-block;
            padding: 12px 24px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            float: right;
            margin-top: 10px;
            cursor: pointer;
        }
        .checkout-btn:hover {
            background-color: #e03a3a;
        }
    </style>
</head>
<body>

<div class="cart-container">
    <h2>Your Shopping Cart</h2>

    <% if (items.isEmpty()) { %>
        <div class="empty-msg">Your cart is currently empty. Go back and add some delicious food!</div>
    <% } else { %>
        <table>
            <tr>
                <th>Item Name</th>
                <th>Qty</th>
                <th>Price</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
            <% for (CartItem item : items) { %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= item.getQuantity() %></td>
                <td>₹<%= item.getPrice() %></td>
                <td>₹<%= item.getTotalPrice() %></td>
                <td>

                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="decrease">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                        <button class="btn">−</button>
                    </form>
                     <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="increase">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                        <button class="btn">+</button>
                    </form>

                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                        <button class="btn btn-remove">Remove</button>
                    </form>
                    
                </td>
            </tr>
            <% } %>
        </table>
		<% if (!items.isEmpty()) { %>
		<%
		    int restaurantId = items.get(0).getRestaurantId(); // Assuming all items are from the same restaurant
		%>
		<form action="Menu" method="get" style="margin-bottom: 15px;">
		    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
		    <button class="checkout-btn" style="background-color: #007bff;">Add More Items</button>
		</form>


    <table>
 </table>

    <div class="total">Total Amount: ₹<%= total %></div>
    
    <form action="Checkout.jsp" method="get">
    	<button type="submit" class="checkout-btn">Place Order</button>
	</form>

<% } %>
    <% } %>
</div>

</body>
</html>
