<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Food_App.Models.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("restaurantName") %> - Menu | FoodApp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Google Fonts & Font Awesome -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #ff4d4d;
            color: white;
            padding: 25px 30px;
            text-align: center;
            font-size: 28px;
            font-weight: bold;
        }

        .container {
            padding: 40px 5%;
        }

        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 25px;
        }

        .card {
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 8px 20px rgba(0,0,0,0.08);
            transition: transform 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
        }

        .card-body {
            padding: 18px;
        }

        .card-body h3 {
            margin: 0;
            font-size: 20px;
            color: #333;
        }

        .card-body p {
            font-size: 14px;
            color: #777;
            margin: 10px 0 5px;
        }

        .card-footer {
            display: flex;
            justify-content: space-between;
            margin-top: 12px;
            align-items: center;
        }

        .price {
            font-size: 16px;
            font-weight: bold;
            color: #222;
        }

        .add-to-cart-btn {
            padding: 8px 16px;
            background: linear-gradient(135deg, #ff4d4d, #e60000);
            color: white;
            border: none;
            border-radius: 30px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s ease;
            font-size: 14px;
            box-shadow: 0 5px 10px rgba(255, 77, 77, 0.3);
        }

        .add-to-cart-btn:hover {
            background: linear-gradient(135deg, #cc0000, #990000);
        }

        @media (max-width: 600px) {
            .header {
                font-size: 22px;
                padding: 20px;
            }

            .container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<div class="header">
    <%= request.getAttribute("restaurantName") %> - Menu
</div>

<div class="container">
    <div class="menu-grid">
        <%
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            if (menuList != null) {
                for (Menu item : menuList) {
        %>
            <div class="card">
                <img src="<%= item.getImagePath() %>" alt="<%= item.getItemName() %>">
                <div class="card-body">
                    <h3><%= item.getItemName() %></h3>
                    <p><%= item.getDescription() %></p>
                    <div class="card-footer">
                        <span class="price">₹<%= item.getPrice() %></span>
                        <form action="cart" method="post" style="margin: 0;">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                            <button type="submit" class="add-to-cart-btn"><i class="fas fa-cart-plus"></i> Add</button>
                        </form>
                    </div>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <p>No menu items found for this restaurant.</p>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
