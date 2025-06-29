<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Food_App.Models.Restaurant, java.util.List" %>
<%@ page import="com.Food_App.DAO.RestaurantDAO, com.Food_App.DAOimpl.RestaurantDAOimpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - FoodApp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Font Awesome & Google Fonts -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: #f2f2f2;
        }

        /* Navbar */
        .navbar {
            background: #ff4d4d;
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .navbar h1 {
            margin: 0;
            font-size: 26px;
            font-weight: 600;
        }

        .navbar nav a {
            color: white;
            text-decoration: none;
            margin-left: 25px;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .navbar nav a:hover {
            color: #fff9c4;
        }

        /* Hero Section */
        .hero {
            background: linear-gradient(to right, #ff4d4d, #ff944d);
            color: white;
            text-align: center;
            padding: 50px 20px;
        }

        .hero h2 {
            font-size: 36px;
            margin-bottom: 10px;
        }

        .hero p {
            font-size: 18px;
        }

        /* Restaurant Grid */
        .container {
            padding: 40px;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 25px;
        }

        .card {
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 5px 20px rgba(0,0,0,0.08);
            transition: transform 0.2s ease;
            text-decoration: none;
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

        .card-body h2 {
            font-size: 20px;
            color: #333;
            margin-bottom: 8px;
        }

        .card-body p {
            margin: 0;
            font-size: 14px;
            color: #666;
        }

        .info {
            margin-top: 10px;
            font-size: 13px;
            display: flex;
            justify-content: space-between;
            color: #999;
        }

        @media (max-width: 600px) {
            .navbar {
                flex-direction: column;
                align-items: flex-start;
            }

            .navbar nav {
                margin-top: 10px;
            }

            .hero h2 {
                font-size: 28px;
            }
        }
    </style>
</head>
<body>

<header class="navbar">
    <h1>FoodApp</h1>
    <nav>
        <a href="Search.jsp"><i class="fas fa-search"></i> Search</a>
        <a href="Help.jsp"><i class="fas fa-question-circle"></i> Help</a>
        <a href="Offers.jsp"><i class="fas fa-tags"></i> Offers</a>
        <% String username = (String) session.getAttribute("user_name"); %>
        <% if (username != null) { %>
            <a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
        <% } else { %>
            <a href="Login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
        <% } %>
        <a href="Cart.jsp"><i class="fas fa-shopping-cart"></i> Cart</a>
    </nav>
</header>

<section class="hero">
    <h2>Delicious food, delivered to you!</h2>
    <p>Explore restaurants near you and place your order now.</p>
</section>

<main class="container">
<%
    RestaurantDAO restaurantDAO = new RestaurantDAOimpl();
    List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

    if (restaurants != null) {
        for (Restaurant restaurant : restaurants) {
%>
    <a href="Menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="card">
        <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>">
        <div class="card-body">
            <h2><%= restaurant.getName() %></h2>
            <p><%= restaurant.getCuisineType() %></p>
            <div class="info">
                <span><%= restaurant.getRating() %> ⭐</span>
                <span>Delivery: <%= restaurant.getDeliveryTime() %></span>
            </div>
        </div>
    </a>
<%
        }
    } else {
%>
    <p>No restaurants available.</p>
<%
    }
%>
</main>

</body>
</html>
